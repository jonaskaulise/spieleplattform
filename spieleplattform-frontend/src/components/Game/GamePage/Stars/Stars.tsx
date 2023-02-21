import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import "./Stars.scss"

export default function Stars(starProps: {count: number}) {

    const stars = Array.from({ length: starProps.count }, (_, index) => <FontAwesomeIcon icon={["fas", "star"]} size={"1x"} key={index} />)

    return (
        <>
            {stars}
        </>
    )
}
