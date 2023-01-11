import {useParams} from "react-router-dom";
import Game from "./Game";
import {useEffect, useState} from "react";
import axios from "axios";

export default function GamePage() {
    const {id} = useParams()

    const [game, setGame] = useState<Game | null>(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get("/game/" + id)
            .then((response) => {
                setGame(response.data)
            })
            .catch(error => {
                setError(error);
            })
    }, [id])

    if (error) return <h1>Game not found</h1>
    if (!game) return null
    return (
        <>
            <h1>{game.name}</h1>
            <h2>{game.developer}</h2>
            <h2>{game.releaseDate.toString()}</h2>
            <ul>
                {game.ratings.map(rating =>
                    <li>
                        <h3>{rating.ratingValue}: {rating.comment}</h3>
                    </li>
                )}
            </ul>
        </>
    );
}