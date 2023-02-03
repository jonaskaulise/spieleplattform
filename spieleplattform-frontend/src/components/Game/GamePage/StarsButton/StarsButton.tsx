import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { Dispatch, SetStateAction } from "react";
import "./StarsButton.scss"

export default function StarsButton(starProps: {count: number, setCount: Dispatch<SetStateAction<number>>}) {

    const stars = Array.from(
        { length: 5}, 
        (_, index) => 
            <FontAwesomeIcon 
                icon={[starProps.count <= index ? "far" : "fas", "star"]} 
                size={"1x"} 
                key={index} 
                onClick={() => starProps.setCount(index + 1)} 
                className="star-button"
            />
    )

    return (
        <>
            {stars}
        </>
    )
}
