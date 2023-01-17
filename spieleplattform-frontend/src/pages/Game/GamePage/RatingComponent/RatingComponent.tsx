import Rating from "../../../Rating/Rating";
import StarComponent from "../StarComponent/StarComponent";
import "./RatingComponent.scss"

export default function RatingComponent(rating: Rating) {
    return (
        <div className="rating-component">
            <StarComponent count={rating.ratingValue}/>
            <p className="rating-comment">
                {rating.comment}
            </p>
        </div>
    )
}