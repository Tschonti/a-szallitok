import { Heading, IconButton, Table, TableContainer, Tbody, Td, Text, Th, Thead, Tr, useToast } from "@chakra-ui/react"
import axios from "axios"
import { useContext, useEffect, useState } from "react"
import { FaCrown, FaTrash } from "react-icons/fa"
import { AuthContext } from "../../auth/AuthContext"
import { Page } from "../../components/Page"
import { User } from "../../types/User"
import { getErrorMessage } from "../../util/errorMessage"

export const UserPage = () => {
    const { user } = useContext(AuthContext)
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

    const promoteUser = async (userId: string) => {
        try {
            const res = await axios.put(`/user/${userId}/promote`)
            if (res.status === 200) {
                toast({
                    title: "User promoted!",
                    status: 'success',
                    duration: 5000
                })
                const userToPromote = users.find(u => u._id === userId)
                if (userToPromote) {
                    userToPromote.isAdmin = true
                    setUsers(users.map(u => u._id === userId ? userToPromote : u))
                }
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
                Users
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
                        {users.length > 0 ? users.map(u => (
                            <Tr key={u._id}>
                                <Td>
                                    {u.name}
                                </Td>
                                <Td>
                                    {u.email}
                                </Td>
                                <Td>
                                {u.phoneNumber}
                                </Td>
                                <Td>
                                    {user?._id!==u._id ? <IconButton marginRight={3} icon={<FaTrash />} onClick={() => deleteUser(u._id)} colorScheme="red" aria-label={`Delete user ${u._id}`} /> : <></>}
                                    {u.isAdmin===false ? <IconButton icon={<FaCrown />} onClick={() => promoteUser(u._id)} colorScheme="green" aria-label={`Delete user ${u._id}`} /> : <></>}
                                </Td>
                            </Tr>
                        )) : <Text>No users found</Text>}
                    </Tbody>
                </Table>

            </TableContainer>
        </Page>
    )
}