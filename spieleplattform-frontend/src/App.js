import './App.css';
import {Link, Route, Routes} from "react-router-dom";
import GameLayout from "./pages/Game/GameLayout";
import Game from "./pages/Game/Game";
import Home from "./pages/Home";

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
                <Route path="/" element={<Home/>}/>
                <Route path="/game" element={<GameLayout/>}>
                    <Route path=":id" element={<Game/>}/>
                </Route>
            </Routes>
        </>
    );
}

export default App;
