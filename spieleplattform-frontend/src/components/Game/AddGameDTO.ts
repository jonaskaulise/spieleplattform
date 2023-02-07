export default interface AddGameDTO {
    name: string
    developer: string
    releaseDate: Date
    description: string
    imageUrl: string
    youtubeId: string
    gameConsoleIds: Array<number>
}
