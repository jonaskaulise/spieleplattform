import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    url: "http://localhost:8081/auth",
    realm: "spiele-plattform",
    clientId: "react-auth",
})

export default keycloak