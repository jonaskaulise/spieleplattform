import "./GameComponent.scss"
import Game from "../Game";
import {useNavigate} from "react-router-dom";

export default function GameComponent(game: Game) {
    const navigate = useNavigate()
    const path = `/games/${game.id}`
    return (
        <div className="game-component">
            <div className="flip-card" onClick={() => navigate(path)}>
                <div className="flip-card-inner">
                    <div className="flip-card-front">
                        <img src={game.imageUrl} alt={game.name}/>
                    </div>
                    <div className="flip-card-back">
                        <div>
                            <h1>{game.name}</h1>
                            <p>{game.developer}</p>
                            <p>{game.releaseDate.toString()}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
