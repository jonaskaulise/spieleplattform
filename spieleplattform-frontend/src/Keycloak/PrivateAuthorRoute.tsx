import { useKeycloak} from "@react-keycloak/web"

const PrivateAuthorRoute = ({ children } : any) => {
    const { keycloak } = useKeycloak()

    const isLoggedIn = keycloak.authenticated
    const isAuthor = keycloak.tokenParsed?.realm_access?.roles.includes("author")

    return isLoggedIn && isAuthor ? children : null
}

export default PrivateAuthorRoute
