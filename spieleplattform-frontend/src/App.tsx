import React from 'react';
import './App.scss';
import {NavLink, Route, Routes, useLocation} from "react-router-dom";
import GameLayoutPage from "./pages/Game/GameLayoutPage";
import GamePage from "./pages/Game/GamePage/GamePage";
import HomePage from "./pages/HomePage";
import AllGamesPage from "./pages/Game/AllGamesPage/AllGamesPage";
import Error from "./pages/Error/Error";
import {fas} from '@fortawesome/free-solid-svg-icons'
import {library} from "@fortawesome/fontawesome-svg-core";
import {useKeycloak} from "@react-keycloak/web";

library.add(fas)

function App() {
    const {keycloak} = useKeycloak()
    const location = useLocation()
    console.log(location)
    return (
        <>
            <nav>
                <div className="flex-container">
                    <ul>
                        <li>
                            <NavLink className={({isActive}) => {
                                return isActive ? "is-active" : "";
                            }} to="/">Home</NavLink>
                        </li>
                        <li>
                            <NavLink reloadDocument className={({isActive}) => { //reloadDocument
                                return isActive ? "is-active" : "";
                            }} to="/games">Games</NavLink>
                        </li>
                    </ul>

                    <div className="align-right">
                        {!keycloak.authenticated && (
                            <button
                                type="button"
                                className="text-blue-800"
                                onClick={() => keycloak.login()}
                            >
                                Login
                            </button>
                        )}

                        {!!keycloak.authenticated && (
                            <button
                                type="button"
                                className="text-blue-800"
                                onClick={() => keycloak.logout()}
                            >
                                Logout {keycloak.tokenParsed?.preferred_username}
                            </button>
                        )}
                    </div>
                </div>

            </nav>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/games" element={<GameLayoutPage/>}>
                    <Route path="" element={<AllGamesPage/>}/>
                    <Route path=":id" element={<GamePage/>}/>
                </Route>
                <Route path="/*" element={<Error message="Error 404"/>}/>
            </Routes>
        </>
    );
}

export default App;
