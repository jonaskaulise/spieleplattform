import "./AddOrEditGame.scss"
import "react-datepicker/dist/react-datepicker.min.css";
import DatePicker from "react-datepicker";
import { useEffect, useState } from "react";
import GameConsole from "../GameConsole";
import axios from "axios";
import { useKeycloak } from "@react-keycloak/web";
import Error from "../../Error/Error";
import GameDTO from "../GameDTO";
import Game from "../Game";


interface AddOrEditGameProps {
    title: string,
    game: Game,
    submitFunction: (gameDTO: GameDTO) => void
}
export default function AddOrEditGame(props: AddOrEditGameProps) {
    const [gameConsoles, setGameConsoles] = useState<GameConsole[] | null>(null)
    const [gameConsoleSelectedFlags, setGameConsoleSelectedFlags] = useState<boolean[]>(new Array(0))
    const [errorStatus, setErrorStatus] = useState(null)
    const { keycloak } = useKeycloak()

    const [name, setName] = useState<string>(props.game.name)
    const [developer, setDeveloper] = useState<string>(props.game.developer)
    const [releaseDate, setReleaseDate] = useState<Date>(props.game.releaseDate)
    const [imageUrl, setImageUrl] = useState<string>(props.game.imageUrl)
    const [youtubeId, setYoutubeId] = useState<string>(props.game.youtubeId)
    const [description, setDescription] = useState<string>(props.game.description)

    useEffect(() => {
        axios.get("/gameConsoles", { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((response) => {
                setGameConsoles(response.data)
                createGameConsolesSelectedFlags(response.data)
            })
            .catch(error => {
                setErrorStatus(error.response.status)
            })
    }, [keycloak.token])

    function createGameConsolesSelectedFlags(gameConsoles: GameConsole[]) {
        const selectedFlags : boolean[] = Array(gameConsoles.length).fill(false)
        const gameConsoleIds : number[] = props.game.gameConsoles.map((gameConsole) => gameConsole.id)
        gameConsoles.forEach((gameConsole, index) => {
            if (gameConsoleIds.includes(gameConsole.id)) {
                selectedFlags[index] = true
            }
        })
        setGameConsoleSelectedFlags(selectedFlags)
    }

    function toggleGameConsoleSelectedFlag(index: number) {
        if (gameConsoleSelectedFlags == null) return

        gameConsoleSelectedFlags[index] = !gameConsoleSelectedFlags[index]
        setGameConsoleSelectedFlags(structuredClone(gameConsoleSelectedFlags))
    }

    function submitGame() {
        if (gameConsoles == null) return

        const gameConsoleIds = new Array<number>(0);
        for(let index = 0; index < gameConsoles?.length; index++) {
            if (gameConsoleSelectedFlags[index]) {
                gameConsoleIds.push(gameConsoles[index].id)
            }
        }

        const gameDTO: GameDTO = {
            name: name,
            developer: developer,
            releaseDate: releaseDate,
            description: description,
            imageUrl: imageUrl,
            youtubeId: youtubeId,
            gameConsoleIds: gameConsoleIds
        }

        props.submitFunction(gameDTO)
    }

    if (errorStatus) {
        return <Error message={"Error " + errorStatus}></Error>
    }
    return gameConsoles && (
        <div className="add-game">
            <h1>{props.title}</h1>
            <form className="add-game-form" onSubmit={submitGame}>
                <div className="add-game-input">
                    <label>Name:</label>
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Developer:</label>
                    <input type="text" value={developer} onChange={(e) => setDeveloper(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Release date:</label>
                    <div className="add-game-datepicker">
                        <DatePicker selected={releaseDate} onChange={(date: Date) => setReleaseDate(date)} />
                    </div>
                </div>
                <div className="add-game-input">
                    <label>Image Url:</label>
                    <input type="text" value={imageUrl} onChange={(e) => setImageUrl(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Youtube Id:</label>
                    <input type="text" value={youtubeId} onChange={(e) => setYoutubeId(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Description:</label>
                    <textarea value={description} onChange={(e) => setDescription(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Game consoles:</label>
                    <div className="add-game-game-consoles">
                        <ul>
                            {gameConsoles.map((gameConsole, index) =>
                                <li
                                    className={ gameConsoleSelectedFlags[index]? "game-console-selected" : "game-console-unselected"}
                                    value={gameConsole.id}
                                    key={gameConsole.id}
                                    onClick={() => {toggleGameConsoleSelectedFlag(index)}}
                                >{gameConsole.name}</li>
                            )}
                        </ul>
                    </div>
                </div>
                <div className="add-game-button-container">
                    <button type="submit" className="sign-in-out-button">{props.title}</button>
                </div>
            </form>
        </div>
    )
}
