import {useKeycloak} from "@react-keycloak/web";
import {NavLink} from "react-router-dom";
import React from "react";
import './Navigation.scss';

function Navigation() {
    const {keycloak} = useKeycloak()

    return (
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
    )
}

export default Navigation
