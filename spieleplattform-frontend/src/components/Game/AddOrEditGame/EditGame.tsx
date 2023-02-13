import AddOrEditGame from "./AddOrEditGame";
import Game from "../Game";
import GameDTO from "../GameDTO";
import axios from "axios";
import {useKeycloak} from "@react-keycloak/web";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import Error from "../../Error/Error";

export default function EditGame() {
    const { id } = useParams()
    const [gameDTO, setGameDTO] = useState<GameDTO>({
        name: "",
        developer: "",
        releaseDate: new Date(),
        description: "",
        imageUrl: "",
        youtubeId: "",
        gameConsoleIds: []
    });
    const [errorStatus, setErrorStatus] = useState(null);
    const { keycloak } = useKeycloak()
    const username = keycloak.tokenParsed?.preferred_username

    useEffect(() => {
        axios.get("/games/" + id, { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((response) => {
                response.data.releaseDate = new Date(response.data.releaseDate) //converts to javascript-date-format
                if (response.data.authorUsername === username) {
                    setGameDTOByGame(response.data)
                }
            })
            .catch(error => {
                setErrorStatus(error.response.status)
            })
    }, [])

    function setGameDTOByGame(game: Game) {
        const newGameDTO: GameDTO = {
            name: game.name,
            developer: game.developer,
            releaseDate: game.releaseDate,
            description: game.description,
            imageUrl: game.imageUrl,
            youtubeId: game.youtubeId,
            gameConsoleIds: game.gameConsoles.map((gameConsole) => gameConsole.id)
        }
        setGameDTO(newGameDTO)
    }

    const submitFunction : (gameDTO: GameDTO) => GameDTO = (gameDTO : GameDTO) => {
        console.log(gameDTO)
        axios.put<Game>(
            "/games/" + id,
            gameDTO,
            { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((response) => {
                console.log(response.data)
            })
            .catch(error => {
                console.log(error)
            })
        return gameDTO
    }

    if (errorStatus) {
        return <Error message={"Error " + errorStatus}></Error>
    }
    if (gameDTO.name === "") {
        return null
    }
    return (
        <AddOrEditGame title={"Edit Game"} game={gameDTO} setGame={setGameDTO} submitFunction={submitFunction}/>
    )
}
