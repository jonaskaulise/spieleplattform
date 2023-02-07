import "./AddGame.scss"
import "react-datepicker/dist/react-datepicker.min.css";
import DatePicker from "react-datepicker";
import { useEffect, useState } from "react";
import GameConsole from "../GameConsole";
import axios from "axios";
import { useKeycloak } from "@react-keycloak/web";
import Error from "../../Error/Error";
import AddGameDTO from "../AddGameDTO";
import Game from "../Game";

export default function AddGame() {
    const [gameConsoles, setGameConsoles] = useState<GameConsole[] | null>(null)
    const [gameConsoleSelectedFlags, setGameConsoleSelectedFlags] = useState<Boolean[]>(new Array(0))
    const [errorStatus, setErrorStatus] = useState(null)
    const { keycloak } = useKeycloak()

    const [name, setName] = useState<string>("")
    const [developer, setDeveloper] = useState<string>("")
    const [releaseDate, setReleaseDate] = useState<Date>(new Date())
    const [imageUrl, setImageUrl] = useState<string>("")
    const [youtubeId, setYoutubeId] = useState<string>("")
    const [description, setDescription] = useState<string>("")

    useEffect(() => {
        axios.get("/gameConsoles", { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((response) => {
                setGameConsoles(response.data)
                setGameConsoleSelectedFlags(Array(response.data.length).fill(false))
            })
            .catch(error => {
                setErrorStatus(error.response.status)
            })
    }, [keycloak.token])

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

        const addGameDTO: AddGameDTO = {
            name: name,
            developer: developer,
            releaseDate: releaseDate,
            description: description,
            imageUrl: imageUrl,
            youtubeId: youtubeId,
            gameConsoleIds: gameConsoleIds
        }

        console.log(addGameDTO)

        axios.post<Game>(
            "/games",
            addGameDTO,
            { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((game) => {

            })
            .catch(error => {
                console.log(error)
            })
    }

    if (errorStatus) {
        return <Error message={"Error " + errorStatus}></Error>
    }
    return gameConsoles && (
        <div className="add-game">
            <h1>Add Game</h1>
            <form className="add-game-form">
                <div className="add-game-input">
                    <label>Name:</label>
                    <input type="text" onChange={(e) => setName(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Developer:</label>
                    <input type="text" onChange={(e) => setDeveloper(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Release date:</label>
                    <div className="add-game-datepicker">
                        <DatePicker selected={releaseDate} onChange={(date: Date) => setReleaseDate(date)} />
                    </div>
                </div>
                <div className="add-game-input">
                    <label>Image Url:</label>
                    <input type="text"  onChange={(e) => setImageUrl(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Youtube Id:</label>
                    <input type="text"  onChange={(e) => setYoutubeId(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Description:</label>
                    <textarea  onChange={(e) => setDescription(e.target.value)}/>
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
                    <button className="sign-in-out-button" onClick={submitGame}>Add game</button>
                </div>
            </form>
        </div>
    )
}
