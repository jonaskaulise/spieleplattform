import {useParams} from "react-router-dom";
import Game from "../Game";
import {useEffect, useState} from "react";
import axios from "axios";
import Error from "../../Error/Error";

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

    if (errorStatus) {
        return <Error message={"Error " + errorStatus}></Error>
    }
    return game && (
        <>
            <div className="game-info">

            </div>
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