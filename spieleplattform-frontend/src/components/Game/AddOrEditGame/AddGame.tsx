import AddOrEditGame from "./AddOrEditGame";
import Game from "../Game";
import GameDTO from "../GameDTO";
import axios from "axios";
import {useKeycloak} from "@react-keycloak/web";

export default function AddGame() {

    const { keycloak } = useKeycloak()

    const emptyGame : Game = {
        authorUsername: "",
        description: "",
        developer: "",
        gameConsoles: [],
        id: 0,
        imageUrl: "",
        name: "",
        ratings: [],
        releaseDate: new Date(),
        youtubeId: ""
    }

    const submitFunction : (gameDTO: GameDTO) => void = (gameDTO : GameDTO) => {
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
    }

    return (
        <AddOrEditGame title={"Add Game"} game={emptyGame} submitFunction={submitFunction}/>
    )
}
