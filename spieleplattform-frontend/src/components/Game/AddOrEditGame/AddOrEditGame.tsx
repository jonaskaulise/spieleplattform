import "./AddOrEditGame.scss"
import "react-datepicker/dist/react-datepicker.min.css";
import DatePicker from "react-datepicker";
import {Dispatch, SetStateAction, SyntheticEvent, useEffect, useState} from "react";
import GameConsole from "../GameConsole";
import axios from "axios";
import { useKeycloak } from "@react-keycloak/web";
import Error from "../../Error/Error";
import GameDTO from "../GameDTO";
import Notification from "../../Notification/Notification";

interface AddOrEditGameProps {
    title: string,
    game: GameDTO,
    setGame: Dispatch<SetStateAction<GameDTO>>,
    submitFunction: (gameDTO: GameDTO) => GameDTO
}
export default function AddOrEditGame(props: AddOrEditGameProps) {
    const [gameConsoles, setGameConsoles] = useState<GameConsole[] | null>(null)
    const [gameConsoleSelectedFlags, setGameConsoleSelectedFlags] = useState<boolean[]>(new Array(0))
    const [errorStatus, setErrorStatus] = useState(null)
    const [showNotification, setShowNotification] = useState<boolean>(false)
    const { keycloak } = useKeycloak()

    useEffect(() => {
        axios.get("/gameConsoles", { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((response) => {
                setGameConsoles(response.data)
                createGameConsolesSelectedFlags(response.data, props.game.gameConsoleIds)
            })
            .catch(error => {
                setErrorStatus(error.response.status)
            })
    }, [])

    function createGameConsolesSelectedFlags(gameConsoles: GameConsole[], selectedGameConsoleIds: number[]) {
        const selectedFlags : boolean[] = Array(gameConsoles.length).fill(false)
        gameConsoles.forEach((gameConsole, index) => {
            if (selectedGameConsoleIds.includes(gameConsole.id)) {
                selectedFlags[index] = true
            }
        })
        setGameConsoleSelectedFlags(selectedFlags)
    }

    function toggleGameConsoleSelectedFlag(index: number) {
        if (gameConsoleSelectedFlags == null || gameConsoles == null) return

        gameConsoleSelectedFlags[index] = !gameConsoleSelectedFlags[index]
        setGameConsoleSelectedFlags(structuredClone(gameConsoleSelectedFlags))
        const gameConsoleId = gameConsoles[index].id
        if (gameConsoleSelectedFlags[index]) {
            props.game.gameConsoleIds.push(gameConsoleId)
        } else {
            const deletionIndex = props.game.gameConsoleIds.indexOf(gameConsoleId)
            props.game.gameConsoleIds.splice(deletionIndex, 1)
        }
        updateGame()
    }

    function submitGame(event: SyntheticEvent) {
        event.preventDefault()

        if (gameConsoles == null) return

        const newGame = props.submitFunction(props.game)
        createGameConsolesSelectedFlags(gameConsoles, newGame.gameConsoleIds)
        showSentNotification()
        return false
    }

    function showSentNotification() {
        setShowNotification(true)
        window.setTimeout(() => {
            setShowNotification(false)
        }, 5000)
    }

    function updateGame() {
        props.setGame(structuredClone(props.game))
    }
    function setName(name: string) {
        props.game.name = name
        updateGame()
    }
    function setDeveloper(developer: string) {
        props.game.developer = developer
        updateGame()
    }
    function setReleaseDate(releaseDate: Date) {
        props.game.releaseDate = releaseDate
        updateGame()
    }
    function setImageUrl(imageUrl: string) {
        props.game.imageUrl = imageUrl
        updateGame()
    }
    function setYoutubeId(youtubeId: string) {
        props.game.youtubeId = youtubeId
        updateGame()
    }
    function setDescription(description: string) {
        props.game.description = description
        updateGame()
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
                    <input type="text" value={props.game.name} onChange={(e) => setName(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Developer:</label>
                    <input type="text" value={props.game.developer} onChange={(e) => setDeveloper(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Release date:</label>
                    <div className="add-game-datepicker">
                        <DatePicker selected={props.game.releaseDate} onChange={(date: Date) => setReleaseDate(date)} />
                    </div>
                </div>
                <div className="add-game-input">
                    <label>Image Url:</label>
                    <input type="text" value={props.game.imageUrl} onChange={(e) => setImageUrl(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Youtube Id:</label>
                    <input type="text" value={props.game.youtubeId} onChange={(e) => setYoutubeId(e.target.value)}/>
                </div>
                <div className="add-game-input">
                    <label>Description:</label>
                    <textarea value={props.game.description} onChange={(e) => setDescription(e.target.value)}/>
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
            {showNotification && <Notification message={"Done"}/>}
        </div>
    )
}
