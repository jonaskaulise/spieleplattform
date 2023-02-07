import Rating from "../Rating/Rating"
import GameConsole from "../GameConsole/GameConsole";

export default interface Game {
    id: number
    name: string
    releaseDate: Date
    developer: string
    author: string
    description: string
    imageUrl: string
    youtubeId: string
    gameConsoles: Array<GameConsole>
    ratings: Array<Rating>
}
