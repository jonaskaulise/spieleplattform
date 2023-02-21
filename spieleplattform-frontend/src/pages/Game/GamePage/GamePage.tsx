import {useParams} from "react-router-dom";
import Game from "../Game";
import {useEffect, useState} from "react";
import axios from "axios";
import Error from "../../Error/Error";
import "./GamePage.scss";
import RatingComponent from "./RatingComponent/RatingComponent";
import YouTube, {YouTubeProps} from "react-youtube";
import {useKeycloak} from "@react-keycloak/web";

export default function GamePage() {
    const {id} = useParams()
    const {keycloak} = useKeycloak()
    const [game, setGame] = useState<Game | null>(null);
    const [errorStatus, setErrorStatus] = useState(null);

    const opts: YouTubeProps['opts'] = {
        width: '100%',
        height: '100%'
    };

    useEffect(() => {
        axios.get("/games/" + id, {headers: { 'Authorization': `Bearer ${keycloak.token}`}})
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
                <img src={game.imgUrl} alt={game.name}/>
                <div className="game-title">
                    <h1>{game.name}</h1>
                    <div className="game-details">
                        <div className="identifier">
                            <h3>Developer:</h3>
                            <h3>ReleaseDate:</h3>
                        </div>
                        <div className="entries">
                            <h3>{game.developer}</h3>
                            <h3>{game.releaseDate.toString()}</h3>
                        </div>
                    </div>
                    <p>{game.description}</p>

                </div>
                <div className="seperator"></div>

                <YouTube videoId={game.youtubeId} opts={opts} className="trailer"></YouTube>
                <div className="seperator"></div>
                <div className="game-ratings">
                    <h1>Ratings:</h1>
                    <ul>
                        {game.ratings.map(rating =>
                            <li key={rating.id}>
                                <RatingComponent ratingValue={rating.ratingValue} comment={rating.comment} id={rating.id}/>
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        </>
    );
}
