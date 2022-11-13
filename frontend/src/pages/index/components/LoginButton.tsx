import { Button } from "@chakra-ui/react"
import { useContext } from "react"
import { FaGoogle } from "react-icons/fa"
import { AuthContext } from "../../../auth/AuthContext"

export const LoginButton = () => {
    const { onLogin } = useContext(AuthContext)
    return (
        <Button leftIcon={<FaGoogle />} colorScheme="red" onClick={onLogin}>Sign in with Google</Button>
    )
}