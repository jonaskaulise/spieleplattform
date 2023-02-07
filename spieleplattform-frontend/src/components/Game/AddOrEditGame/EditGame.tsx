import AddOrEditGame from "./AddOrEditGame";
import Game from "../Game";
import GameDTO from "../GameDTO";
import axios from "axios";
import {useKeycloak} from "@react-keycloak/web";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import Error from "../../Error/Error";

export default function EditGame() {
    const { id } = useParams()
    const [game, setGame] = useState<Game | null>(null);
    const [errorStatus, setErrorStatus] = useState(null);
    const { keycloak } = useKeycloak()
    const navigate = useNavigate()
    const username = keycloak.tokenParsed?.preferred_username

    useEffect(() => {
        axios.get("/games/" + id, { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((response) => {
                response.data.releaseDate = new Date(response.data.releaseDate) //converts to javascript-date-format
                if (response.data.authorUsername !== username) {
                    setGame(null)
                } else {
                    setGame(response.data)
                }
            })
            .catch(error => {
                setErrorStatus(error.response.status)
            })
    }, [])

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
        navigate("/games")
    }

    if (errorStatus) {
        return <Error message={"Error " + errorStatus}></Error>
    }
    return game && (
        <AddOrEditGame title={"Edit Game"} game={game} submitFunction={submitFunction}/>
    )
}
