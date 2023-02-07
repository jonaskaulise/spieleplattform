export default interface GameDTO {
    name: string
    developer: string
    releaseDate: Date
    description: string
    imageUrl: string
    youtubeId: string
    gameConsoleIds: Array<number>
}
