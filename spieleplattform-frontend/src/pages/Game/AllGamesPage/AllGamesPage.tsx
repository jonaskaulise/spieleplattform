import Game from "../Game";
import {useEffect, useState} from "react";
import axios from "axios";
import GameComponent from "../GameComponent/GameComponent";
import "./AllGamesPage.css"
import Error404Page from "../../Error/Error404Page";
import ErrorPage from "../../Error/ErrorPage";

export default function AllGamesPage() {
    const [games, setGames] = useState<Game[] | null>(null);
    const [errorStatus, setErrorStatus] = useState(null);

    useEffect(() => {
        axios.get("/game")
            .then((response) => {
                setGames(response.data)
            })
            .catch(error => {
                setErrorStatus(error.response.status);
            })
    }, [])

    if (errorStatus)
        return errorStatus === 404 ? <Error404Page/> : <ErrorPage/>
    return games && (
        <div className="flex-container">
            {games.map(game =>
                <GameComponent
                    key={game.id}
                    id={game.id}
                    name={game.name}
                    releaseDate={game.releaseDate}
                    developer={game.developer}
                    imgUrl={game.imgUrl}
                    ratings={game.ratings}
                />
            )}
        </div>
    );
}