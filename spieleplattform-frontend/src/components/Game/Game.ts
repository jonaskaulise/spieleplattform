import Rating from "../Rating/Rating"
import GameConsole from "../GameConsole/GameConsole";

export default interface Game {
    id: number
    name: string
    releaseDate: Date
    developer: string
    description: string
    imgUrl: string
    youtubeId: string
    gameConsoles: Array<GameConsole>
    ratings: Array<Rating>
}