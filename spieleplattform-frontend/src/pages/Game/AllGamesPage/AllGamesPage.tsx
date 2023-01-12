import Game from "../Game";
import {useEffect, useState} from "react";
import axios from "axios";
import GameComponent from "../GameComponent/GameComponent";
import "./AllGamesPage.css"

export default function AllGamesPage() {
    const [games, setGames] = useState<Game[] | null>(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get("/game")
            .then((response) => {
                setGames(response.data)
            })
            .catch(error => {
                setError(error);
            })
    }, [])

    if (error) return <h1>Games not found</h1>
    if (!games) return null
    return (
        <div className="flex-container">
            {games.map(game =>
                <GameComponent
                    name={game.name}
                    releaseDate={game.releaseDate}
                    developer={game.developer}
                    ratings={game.ratings}
                />
            )}
        </div>
    );
}