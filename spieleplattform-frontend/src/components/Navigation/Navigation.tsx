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
                        <NavLink className={({isActive}) =>
                            isActive ? "is-active" : ""
                        } to="/">Home</NavLink>
                    </li>
                    <li>
                        <NavLink className={({isActive}) =>
                            isActive ? "is-active" : ""
                        } to="/games">Games</NavLink>
                    </li>
                </ul>

                <div className="align-right">
                    {!keycloak.authenticated && (
                        <button
                            type="button"
                            className="sign-up-button"
                            onClick={() => keycloak.register()}
                        >
                            Sign up
                        </button>
                    )}

                    {!keycloak.authenticated && (
                        <button
                            type="button"
                            className="sign-in-out-button"
                            onClick={() => keycloak.login()}
                        >
                            Sign in
                        </button>
                    )}

                    {keycloak.authenticated && (
                        <button
                            type="button"
                            className="sign-in-out-button"
                            onClick={() => keycloak.logout()}
                        >
                            Sign out
                        </button>
                    )}
                </div>
            </div>
        </nav>
    )
}

export default Navigation
