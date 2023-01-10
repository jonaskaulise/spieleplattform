import React from 'react';
import logo from './logo.svg';
import './App.css';
import {Link, Route, Routes} from "react-router-dom";
import GameLayoutPage from "./pages/Game/GameLayoutPage";
import GamePage from "./pages/Game/GamePage";
import HomePage from "./pages/HomePage";

function App() {
  return (
      <>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/game/new">New Game</Link>
            </li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<HomePage/>}/>
          <Route path="/game" element={<GameLayoutPage/>}>
            <Route path=":id" element={<GamePage/>}/>
          </Route>
        </Routes>
      </>
  );
}

export default App;
