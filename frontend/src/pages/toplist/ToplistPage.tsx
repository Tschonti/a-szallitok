import { Heading, Table, TableContainer, Tbody, Td, Text, Th, Thead, Tr, useToast } from "@chakra-ui/react"
import axios from "axios"
import { useContext, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { AuthContext } from "../../auth/AuthContext"
import { Page } from "../../components/Page"
import { UserInToplist } from "../../types/User"
import { getErrorMessage } from "../../util/errorMessage"

export const ToplistPage = () => {
    const toast = useToast()
    const [topUsers, setTopUsers] = useState<UserInToplist[]>([])
    const { user } = useContext(AuthContext)
    const navigate = useNavigate()

    if (!user) {
        navigate('/')
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await axios.get<UserInToplist[]>('/user/toplist')
                if (res.status === 200) {
                    setTopUsers(res.data.sort((a, b) => b.moneyEarned - a.moneyEarned || b.deliveriesCompleted - a.deliveriesCompleted))
                }
            } catch(error) {
                toast({
                    title: 'Error fetching toplist',
                    description: getErrorMessage(error),
                    status: 'error',
                    duration: 5000
                })
            }
        }
        fetchData()
    }, [toast])


    return (
        <Page>
            <Heading my={3}>
                Toplist
            </Heading>

            <TableContainer>
                <Table variant="striped" colorScheme="blue">
                    <Thead>
                        <Tr>
                            <Th>
                                Rank
                            </Th>
                            <Th>
                                Name
                            </Th>
                            <Th>
                                Deliveries Completed
                            </Th>
                            <Th>
                                Money Earned
                            </Th>
                        </Tr>
                    </Thead>
                    <Tbody>
                        {topUsers.length > 0 && topUsers.map((topUser, index) => (
                            <Tr key={topUser._id._id}>
                                <Td>
                                {index + 1}
                                </Td>
                                <Td>
                                    {topUser._id.name}
                                </Td>
                                <Td>
                                    {topUser.deliveriesCompleted}
                                </Td>
                                <Td>
                                {topUser.moneyEarned}
                                </Td>
                            </Tr>
                        ))}
                    </Tbody>
                </Table>
            </TableContainer>
            {topUsers.length === 0 && <Text>No top users found</Text>}
        </Page>
    )
}