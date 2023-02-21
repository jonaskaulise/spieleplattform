import {Link, useParams} from "react-router-dom";
import Game from "../Game";
import React, { useEffect, useState } from "react";
import axios from "axios";
import Error from "../../Error/Error";
import "./GamePage.scss";
import RatingComponent from "./RatingComponent/RatingComponent";
import YouTube, { YouTubeProps } from "react-youtube";
import { useKeycloak } from "@react-keycloak/web";
import AddRating from "./AddRating/AddRating";
import Rating from "../../Rating/Rating";
import RatingDTO from "../../Rating/RatingDTO";

export default function GamePage() {
    const { id } = useParams()
    const { keycloak } = useKeycloak()
    const [game, setGame] = useState<Game | null>(null);
    const [errorStatus, setErrorStatus] = useState(null);

    const username = keycloak.tokenParsed?.preferred_username
    const opts: YouTubeProps['opts'] = {
        width: '100%',
        height: '100%'
    };

    useEffect(() => {
        axios.get("/games/" + id, { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((response) => {
                setGame(response.data)
            })
            .catch(error => {
                setErrorStatus(error.response.status);
            })
    }, [id])

    function isTheAuthor(): boolean {
        if (game === null) {
            return false
        }
        return game.authorUsername === username ;
    }

    function submitRatingDTO(ratingDTO: RatingDTO) {
        if (game === null) return

        ratingDTO.gameId = game.id
        axios.post<Rating>(
            "/ratings",
            ratingDTO,
            { headers: { 'Authorization': `Bearer ${keycloak.token}` } })
            .then((rating) => {
                game.ratings.push(rating.data)
                setGame(structuredClone(game))
            })
            .catch(error => {
                console.log(error)
            })
    }

    function userAlreadyPostedRating(username: string) : boolean {
        if (game === null) return false
        return game.ratings.some((rating) => rating.username === username)
    }

    if (errorStatus) {
        return <Error message={"Error " + errorStatus}></Error>
    }
    return game && (
        <>
            <div className="game-info">
                <img src={game.imageUrl} alt={game.name} />
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
                    {isTheAuthor() &&
                        <Link className="edit-game-button" to={"/games/edit/" + id}>Edit Game</Link>
                    }
                </div>
                <div className="separator"></div>

                <YouTube videoId={game.youtubeId} opts={opts} className="trailer"></YouTube>
                <div className="separator"></div>
                <div className="game-ratings">
                    <h1>Ratings:</h1>
                    <ul>
                        {game.ratings.map(rating =>
                            <li key={rating.id}>
                                <RatingComponent
                                    graphicRating={rating.graphicRating}
                                    soundRating={rating.soundRating}
                                    addictionRating={rating.addictionRating}
                                    actionRating={rating.actionRating}
                                    username={rating.username}
                                    comment={rating.comment}
                                    id={rating.id}
                                />
                            </li>
                        )}
                    </ul>
                </div>
                {!userAlreadyPostedRating(keycloak.tokenParsed?.preferred_username) &&
                    <>
                        <div className="separator" />
                        <AddRating submitRatingDTO={submitRatingDTO} />
                    </>
                }
            </div>
        </>
    );
}
