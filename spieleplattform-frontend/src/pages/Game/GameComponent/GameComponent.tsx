import "./GameComponent.scss"
import Game from "../Game";
import {useNavigate} from "react-router-dom";

export default function GameComponent({name, developer, releaseDate, imgUrl, id}: Game) {
    const navigate = useNavigate()
    const path = `/games/${id}`
    return (
        <div className="game-component">
            <div className="flip-card" onClick={() => navigate(path)}>
                <div className="flip-card-inner">
                    <div className="flip-card-front">
                        <img
                            src={imgUrl} alt={name}/>
                    </div>
                    <div className="flip-card-back">
                        <h1>{name}</h1>
                        <p>{developer}</p>
                        <p>{releaseDate.toString()}</p>
                    </div>
                </div>
            </div>
        </div>
    );
}