import './App.scss';
import {Route, Routes} from "react-router-dom";
import GameLayoutPage from "./pages/Game/GameLayoutPage";
import GamePage from "./pages/Game/GamePage/GamePage";
import HomePage from "./pages/HomePage";
import AllGamesPage from "./pages/Game/AllGamesPage/AllGamesPage";
import Error from "./pages/Error/Error";
import {fas} from '@fortawesome/free-solid-svg-icons'
import {library} from "@fortawesome/fontawesome-svg-core";
import PrivateRoute from "./Keycloak/PrivateRoute";
import Navigation from "./pages/Navigation/Navigation";

library.add(fas)

function App() {
    return (
        <>
            <Navigation/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/games" element={<PrivateRoute><GameLayoutPage/></PrivateRoute>}>
                    <Route path="" element={<AllGamesPage/>}/>
                    <Route path=":id" element={<GamePage/>}/>
                </Route>
                <Route path="/*" element={<Error message="Error 404"/>}/>
            </Routes>
        </>
    );
}

export default App;
