import AddOrEditGame from "./AddOrEditGame";
import Game from "../Game";
import GameDTO from "../GameDTO";
import axios from "axios";
import {useKeycloak} from "@react-keycloak/web";
import {useState} from "react";

export default function AddGame() {

    const { keycloak } = useKeycloak()

    const emptyGameDTO : GameDTO = {
        name: "",
        developer: "",
        releaseDate: new Date(),
        description: "",
        imageUrl: "",
        youtubeId: "",
        gameConsoleIds: []
    }
    const [gameDTO, setGameDTO] = useState<GameDTO>(emptyGameDTO)

    const submitFunction : (gameDTO: GameDTO) => GameDTO = (gameDTO : GameDTO) => {
        axios.post<Game>(
            "/games",
            gameDTO,
            { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((response) => {
                console.log(response.data)
            })
            .catch(error => {
                console.log(error)
            })
        const newGameDTO = structuredClone(emptyGameDTO)
        setGameDTO(newGameDTO)
        return newGameDTO
    }

    return (
        <AddOrEditGame title={"Add Game"} game={gameDTO} setGame={setGameDTO} submitFunction={submitFunction}/>
    )
}
