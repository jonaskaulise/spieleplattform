import Rating from "../Rating/Rating"

export default interface GamePage {
    name: string;
    releaseDate: Date;
    developer: string;
    ratings: Array<Rating>;

}