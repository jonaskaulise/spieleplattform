import React from 'react';
import './App.scss';
import {NavLink, Route, Routes} from "react-router-dom";
import GameLayoutPage from "./pages/Game/GameLayoutPage";
import GamePage from "./pages/Game/GamePage";
import HomePage from "./pages/HomePage";
import ErrorPage from "./pages/Error/Error404Page";
import AllGamesPage from "./pages/Game/AllGamesPage/AllGamesPage";

function App() {
    return (
        <>
            <nav>
                <ul>
                    <li>
                        <NavLink className={({ isActive}) => {
                            return isActive ? "is-active" : ""
                        }} to="/">Home</NavLink>
                    </li>
                    <li>
                        <NavLink className={({ isActive}) => {
                            return isActive ? "is-active" : ""
                        }} to="/games">Games</NavLink>
                    </li>
                </ul>
            </nav>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/games" element={<GameLayoutPage/>}>
                    <Route path="" element={<AllGamesPage/>}/>
                    <Route path=":id" element={<GamePage/>}/>
                </Route>
                <Route path="/*" element={<ErrorPage/>}/>
            </Routes>
        </>
    );
}

export default App;
