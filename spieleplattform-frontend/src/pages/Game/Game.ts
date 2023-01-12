import Rating from "../Rating/Rating"

export default interface Game {
    id: number;
    name: string;
    releaseDate: Date;
    developer: string;
    imgUrl: string;
    ratings: Array<Rating>;

}