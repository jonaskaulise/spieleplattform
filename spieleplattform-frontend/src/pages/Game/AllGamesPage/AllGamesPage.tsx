import Game from "../Game";
import GameConsole from "../GameConsole";
import {ChangeEvent, useEffect, useState} from "react";
import axios from "axios";
import GameComponent from "../GameComponent/GameComponent";
import "./AllGamesPage.scss"
import {useSearchParams} from "react-router-dom";
import Error from "../../Error/Error";

export default function AllGamesPage() {
    const [games, setGames] = useState<Game[] | null>(null)
    const [gameConsoles, setGameConsoles] = useState<GameConsole[] | null>(null)
    const [errorStatus, setErrorStatus] = useState(null)

    const [searchParams, setSearchParams] = useSearchParams()
    const gameConsoleIdValue = Number(searchParams.get('gameConsoleId'))
    const gameConsoleId = isNaN(gameConsoleIdValue) ? 0 : gameConsoleIdValue
    const nameSearchValue = searchParams.get('nameSearch')
    const nameSearch = nameSearchValue == null ? "" : nameSearchValue.toString()

    useEffect(() => {
        if (gameConsoleId === 0) {
            searchParams.delete('gameConsoleId')
        }
        if (nameSearch === "") {
            searchParams.delete('nameSearch')
        }
        setSearchParams(searchParams)

        axios.get("/gameConsoles")
            .then((response) => {
                setGameConsoles(response.data)
            })
            .catch(error => {
                setErrorStatus(error.response.status)
            })

        axios.get('/games', {params: searchParams})
            .then((response) => {
                setGames(response.data)
            })
            .catch(error => {
                setErrorStatus(error.response.status)
            })
    }, [gameConsoleId, nameSearch, searchParams, setSearchParams])

    function onSelectGameConsoleChange(event: ChangeEvent<HTMLSelectElement>) {
        const value = event.target.value
        if (value === "0") {
            searchParams.delete('gameConsoleId')
        } else {
            searchParams.set('gameConsoleId', value)
        }
        setSearchParams(searchParams)
    }

    function onInputNameSearchChange(event: ChangeEvent<HTMLInputElement>) {
        const value = event.target.value
        if (value === "") {
            searchParams.delete('nameSearch')
        } else {
            searchParams.set('nameSearch', value)
        }
        setSearchParams(searchParams)
    }

    if (errorStatus) {
        return <Error message={"Error " + errorStatus}></Error>
    }
    return games && gameConsoles && (
        <>
            <form className="filter-form" onSubmit={event => event.preventDefault()}>

                <select name="gameConsoles" id="gameConsoles" value={gameConsoleId} onChange={onSelectGameConsoleChange}>
                    <option value="0" key="0">no filter</option>
                    {gameConsoles.map(gameConsole =>
                        <option value={gameConsole.id} key={gameConsole.id}>{gameConsole.name}</option>
                    )}
                </select>

                <input type="text" name="nameSearch" defaultValue={nameSearch} placeholder="search" onChange={onInputNameSearchChange}/>

            </form>

            <div className="flex-container">
                {games.map(game =>
                    <GameComponent
                        key={game.id}
                        id={game.id}
                        name={game.name}
                        releaseDate={game.releaseDate}
                        developer={game.developer}
                        description={game.description}
                        imgUrl={game.imgUrl}
                        youtubeId={game.youtubeId}
                        gameConsoles={game.gameConsoles}
                        ratings={game.ratings}
                    />
                )}
            </div>
        </>
    );
}