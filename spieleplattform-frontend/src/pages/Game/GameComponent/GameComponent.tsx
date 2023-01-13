import "./GameComponent.css"
import Game from "../Game";

export default function GameComponent({name, developer, releaseDate, imgUrl}: Game) {

    return (
        <div className="flip-card">
            <div className="flip-card-inner">
                <div className="flip-card-front">
                    <img
                        src={imgUrl} alt="{name}"/>
                </div>
                <div className="flip-card-back">
                    <h1>{name}</h1>
                    <p>{developer}</p>
                    <p>{releaseDate.toString()}</p>
                </div>
            </div>
        </div>
    );
}