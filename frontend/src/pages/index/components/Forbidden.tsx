import { Heading, Center } from "@chakra-ui/react"
import { useContext } from "react"
import { AuthContext } from "../../../auth/AuthContext"

export const Forbidden = () => {
    const { user } = useContext(AuthContext)

    return (
        <Center>
            <Heading>{user?.name}, you are not an admin!</Heading>
        </Center>
    )
}