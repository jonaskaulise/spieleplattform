import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import "./StarComponent.scss"

export default function StarComponent(starProps: {count: number}) {

    const stars = Array.from({ length: starProps.count }, (_, index) => <FontAwesomeIcon icon={["fas", "star"]} size={"2x"} key={index} />)

    return (
        <div className="star-component">
            {stars}
        </div>
    )
}
