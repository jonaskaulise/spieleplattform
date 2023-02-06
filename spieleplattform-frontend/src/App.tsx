import './App.scss';
import {Route, Routes} from "react-router-dom";
import GameLayoutPage from "./components/Game/GameLayoutPage";
import GamePage from "./components/Game/GamePage/GamePage";
import HomePage from "./components/HomePage";
import AllGamesPage from "./components/Game/AllGamesPage/AllGamesPage";
import Error from "./components/Error/Error";
import {fas} from '@fortawesome/free-solid-svg-icons'
import {far} from '@fortawesome/free-regular-svg-icons'
import {library} from "@fortawesome/fontawesome-svg-core";
import PrivateRoute from "./Keycloak/PrivateRoute";
import Navigation from "./components/Navigation/Navigation";
import AddGame from './components/Game/AddGame/AddGame';

library.add(fas, far)

function App() {
    return (
        <>
            <Navigation/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/games" element={<PrivateRoute><GameLayoutPage/></PrivateRoute>}>
                    <Route path="" element={<AllGamesPage/>}/>
                    <Route path=":id" element={<GamePage/>}/>
                    <Route path="add" element={<AddGame/>}/>
                </Route>
                <Route path="/*" element={<Error message="Error 404"/>}/>
            </Routes>
        </>
    );
}

export default App;
