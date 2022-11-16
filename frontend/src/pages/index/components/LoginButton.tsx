import { Button, Flex } from "@chakra-ui/react"
import { useContext } from "react"
import { FaGoogle } from "react-icons/fa"
import { AuthContext } from "../../../auth/AuthContext"

export const LoginButton = () => {
    const { onLogin } = useContext(AuthContext)
    return (
    <Flex justify="center" alignContent="center" my={20}>
        <Button size="lg" leftIcon={<FaGoogle />} colorScheme="red" onClick={onLogin}>Sign in with Google</Button>
    </Flex>
    )
}