import Rating from "../../../Rating/Rating";
import Stars from "../Stars/Stars";
import "./RatingComponent.scss"

export default function RatingComponent(rating: Rating) {

    return (
        <div className="rating-component">
            <p>
                <b>{rating.username}</b>
            </p>
            <table>
                <tbody>
                    <tr>
                        <td className="key"><i>Graphics:</i></td>
                        <td className="value"><Stars count={rating.graphicRating} /></td>
                    </tr>
                    <tr>
                        <td className="key"><i>Sound:</i></td>
                        <td className="value"><Stars count={rating.soundRating} /></td>
                    </tr>
                    <tr>
                        <td className="key"><i>Addiction:</i></td>
                        <td className="value"><Stars count={rating.addictionRating} /></td>
                    </tr>
                    <tr>
                        <td className="key"><i>Action:</i></td>
                        <td className="value"><Stars count={rating.actionRating} /></td>
                    </tr>
                </tbody>
            </table>
            <p className="rating-comment">
                {rating.comment}
            </p>
        </div>
    )
}