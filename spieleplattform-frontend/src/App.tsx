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
import PrivateAuthorRoute from "./Keycloak/PrivateAuthorRoute";
import AddGame from "./components/Game/AddOrEditGame/AddGame";
import EditGame from "./components/Game/AddOrEditGame/EditGame";

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
                    <Route path="add" element={<PrivateAuthorRoute><AddGame/></PrivateAuthorRoute>}/>
                    <Route path="edit/:id" element={<PrivateAuthorRoute><EditGame/></PrivateAuthorRoute>}/>
                </Route>
                <Route path="/*" element={<Error message="Error 404"/>}/>
            </Routes>
        </>
    );
}

export default App;
