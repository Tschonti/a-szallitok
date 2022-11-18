import { Heading, IconButton, Table, TableContainer, Tbody, Td, Text, Th, Thead, Tr, useToast } from "@chakra-ui/react"
import axios from "axios"
import { useEffect, useState } from "react"
import { FaTrash } from "react-icons/fa"
import { Page } from "../../components/Page"
import { User } from "../../types/User"
import { getErrorMessage } from "../../util/errorMessage"

export const UserPage = () => {
    const toast = useToast()
    const [users, setUsers] = useState<User[]>([])

    const deleteUser = async (userId: string) => {
        try {
            const res = await axios.delete(`/user/${userId}`)
            if (res.status === 200) {
                toast({
                    title: "User deleted!",
                    status: 'success',
                    duration: 5000
                })
                setUsers(users?.filter(d => d._id !== userId))
            }
        } catch(e) {
            toast({
                title: 'Failed to delete user!',
                description: getErrorMessage(e),
                status: 'error',
                duration: 5000
            })
        }
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await axios.get<User[]>('/user')
                if (res.status === 200) {
                    setUsers(res.data)
                }
            } catch(error) {
                toast({
                    title: 'Error fetching users',
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
                Deliveries
            </Heading>

            <TableContainer>
                <Table variant="striped" colorScheme="blue">
                    <Thead>
                        <Tr>
                            <Th>
                                Name
                            </Th>
                            <Th>
                                Email
                            </Th>
                            <Th>
                                Phone Number
                            </Th>
                            <Th>
                                Actions
                            </Th>
                        </Tr>
                    </Thead>
                    <Tbody>
                        {users.length > 0 ? users.map(user => (
                            <Tr key={user._id}>
                                <Td>
                                    {user.name}
                                </Td>
                                <Td>
                                    {user.email}
                                </Td>
                                <Td>
                                {user.phoneNumber}
                                </Td>
                                <Td>
                                    <IconButton icon={<FaTrash />} onClick={() => deleteUser(user._id)} colorScheme="red" aria-label={`Delete user ${user._id}`} />
                                </Td>
                            </Tr>
                        )) : <Text>No users found</Text>}
                    </Tbody>
                </Table>

            </TableContainer>
        </Page>
    )
}