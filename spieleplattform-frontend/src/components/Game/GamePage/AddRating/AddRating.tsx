import { useState } from "react";
import RatingDTO from "../../../Rating/RatingDTO";
import StarsButton from "../StarsButton/StarsButton";
import "./AddRating.scss"

interface AddRatingProps {
    submitRatingDTO: (ratingDTO: RatingDTO) => void
}
function AddRating(props: AddRatingProps) {

    const [graphicRating, setGraphicRating] = useState(1)
    const [soundRating, setSoundRating] = useState(1)
    const [addictionRating, setAddictionRating] = useState(1)
    const [actionRating, setActionRating] = useState(1)
    const [comment, setComment] = useState("")

    function submitRating() {
        const newRating: RatingDTO = {
            graphicRating: graphicRating,
            soundRating: soundRating,
            addictionRating: addictionRating,
            actionRating: actionRating,
            gameId: -1,
            comment: comment,
        }
        setGraphicRating(1)
        setSoundRating(1)
        setAddictionRating(1)
        setActionRating(1)
        setComment("")
        props.submitRatingDTO(newRating)
    }

    return (
        <>
            <h1>Add Rating:</h1>
            <div className="add-rating">
                <table>
                    <tbody>
                        <tr>
                            <td className="key"><i>Graphics:</i></td>
                            <td className="value"><StarsButton count={graphicRating} setCount={setGraphicRating} /></td>
                        </tr>
                        <tr>
                            <td className="key"><i>Sound:</i></td>
                            <td className="value"><StarsButton count={soundRating} setCount={setSoundRating} /></td>
                        </tr>
                        <tr>
                            <td className="key"><i>Addiction:</i></td>
                            <td className="value"><StarsButton count={addictionRating} setCount={setAddictionRating} /></td>
                        </tr>
                        <tr>
                            <td className="key"><i>Action:</i></td>
                            <td className="value"><StarsButton count={actionRating} setCount={setActionRating} /></td>
                        </tr>
                    </tbody>
                </table>
                <textarea value={comment} onChange={(e) => setComment(e.target.value)} />
                <div className="add-rating-button-container">
                    <button className="sign-in-out-button" onClick={submitRating}>Add comment</button>
                </div>
            </div>
        </>
    );
}

export default AddRating