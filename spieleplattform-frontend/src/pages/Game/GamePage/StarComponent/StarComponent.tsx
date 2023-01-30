import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import "./StarComponent.scss"

export default function StarComponent(starProps: {count: number}) {

    const stars = []
    for(let index = 0; index < starProps.count; index++) {
        stars.push(
            <FontAwesomeIcon icon={["fas", "star"]} size={"2x"} key={index}/>
        )
    }

    return (
        <div className="star-component">
            {stars}
        </div>
    )
}