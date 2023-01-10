import {useParams} from "react-router-dom";
import Game from "./Game";
import Rating from "../Rating/Rating";

export default function GamePage() {
    const {id} = useParams()
    
    const game: Game = {
        name: "Minecraft",
        releaseDate: new Date(2011, 11, 18),
        developer: "Mojang Studios",
        ratings: [
            {
                ratingValue: 5,
                comment: "Best game ever!",
            }
        ]
    }
    
    return (
        <>
            <h1>{game.name}</h1>
            <h2>{game.developer}</h2>
            <h2>{game.releaseDate.toString()}</h2>
        </>
    );
}