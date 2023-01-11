import React from 'react';
import './App.css';
import {Link, Route, Routes} from "react-router-dom";
import GameLayoutPage from "./pages/Game/GameLayoutPage";
import GamePage from "./pages/Game/GamePage";
import HomePage from "./pages/HomePage";
import ErrorPage from "./pages/ErrorPage";

function App() {
    return (
        <>
            <nav>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                </ul>
            </nav>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/game" element={<GameLayoutPage/>}>
                    <Route path=":id" element={<GamePage/>}/>
                </Route>
                <Route path="/*" element={<ErrorPage/>}/>
            </Routes>
        </>
    );
}

export default App;
