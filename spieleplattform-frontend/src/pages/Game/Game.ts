import Rating from "../Rating/Rating"

export default interface Game {
    name: string;
    releaseDate: Date;
    developer: string;
    imgUrl: string;
    ratings: Array<Rating>;

}