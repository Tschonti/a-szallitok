import { Button, Center } from "@chakra-ui/react"
import axios from "axios"
import { useContext } from "react"
import { AuthContext } from "../../auth/AuthContext"
import { Delivery } from "../../types/Delivery"
import { Page } from "../../components/Page"

export const MapPage = () => {
    const { onLogout } = useContext(AuthContext)
    const getDeliveries = async () => {
        console.log((await axios.get<Delivery>('/user/toplist')).data)
      }
    return (
        <Page>
            <Center>
                <Button onClick={getDeliveries}>Get deliveries</Button>
                <Button onClick={onLogout}>Logout</Button>
            </Center>
        </Page>
    )
}