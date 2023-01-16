import {useParams} from "react-router-dom";
import Game from "./Game";
import {useEffect, useState} from "react";
import axios from "axios";
import Error404Page from "../Error/Error404Page";
import ErrorPage from "../Error/ErrorPage";

export default function GamePage() {
    const {id} = useParams()

    const [game, setGame] = useState<Game | null>(null);
    const [errorStatus, setErrorStatus] = useState(null);

    useEffect(() => {
        axios.get("/games/" + id)
            .then((response) => {
                setGame(response.data)
            })
            .catch(error => {
                setErrorStatus(error.response.status);
            })
    }, [id])

    if (errorStatus)
        return errorStatus === 404 ? <Error404Page/> : <ErrorPage/>
    return game && (
        <>
            <h1>{game.name}</h1>
            <h2>{game.developer}</h2>
            <h2>{game.releaseDate.toString()}</h2>
            <ul>
                {game.ratings.map(rating =>
                    <li key={rating.id}>
                        <h3>{rating.ratingValue}: {rating.comment}</h3>
                    </li>
                )}
            </ul>
        </>
    );
}