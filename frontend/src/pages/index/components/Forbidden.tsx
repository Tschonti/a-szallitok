import { Heading, Button } from "@chakra-ui/react"
import { useContext } from "react"
import { AuthContext } from "../../../auth/AuthContext"

export const Forbidden = () => {
    const { user, onLogout } = useContext(AuthContext)

    return (
        <>
            <Heading>{user?.name}, you are not an admin!</Heading>
            <Button onClick={onLogout}>Logout</Button>
        </>
    )
}