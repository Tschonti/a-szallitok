import { Heading, IconButton, Table, TableContainer, Tbody, Td, Text, Th, Thead, Tr, useDisclosure, useToast } from "@chakra-ui/react"
import axios from "axios"
import { useEffect, useState } from "react"
import { FaTrash } from "react-icons/fa"
import { DeleteModal } from "../../components/DeleteModal"
import { Page } from "../../components/Page"
import { Delivery } from "../../types/Delivery"
import { getErrorMessage } from "../../util/errorMessage"

export const DeliveryPage = () => {
    const toast = useToast()
    const [deliveries, setDeliveries] = useState<Delivery[]>([])
    const { isOpen, onClose, onOpen } = useDisclosure()
    const [idToDelete, setIdToDelete] = useState('')

    const openModal = (id: string) => {
        setIdToDelete(id)
        onOpen()
    }

    const deleteDelivery = async (deliveryId: string) => {
        try {
            const res = await axios.delete(`/delivery/${deliveryId}`)
            if (res.status === 200) {
                toast({
                    title: "Delivery deleted!",
                    status: 'success',
                    duration: 5000
                })
                setDeliveries(deliveries?.filter(d => d._id !== deliveryId))
            }
        } catch(e) {
            toast({
                title: 'Failed to delete delivery!',
                description: getErrorMessage(e),
                status: 'error',
                duration: 5000
            })
        }
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await axios.get<Delivery[]>('/allDeliveries')
                if (res.status === 200) {
                    setDeliveries(res.data)
                }
            } catch(error) {
                toast({
                    title: 'Error fetching deliveries',
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
                              Client
                            </Th>
                            <Th>
                              Source
                            </Th>
                            <Th>
                              Destination
                            </Th>
                            <Th>
                              Status
                            </Th>
                            <Th>
                              Transporter
                            </Th>
                            <Th>
                              Actions
                            </Th>
                        </Tr>
                    </Thead>
                    <Tbody>
                        {deliveries.length > 0 ? deliveries.map(d => (
                            <Tr key={d._id}>
                                <Td>
                                   {d.clientUser.name}
                                </Td>
                                <Td>
                                   {`${d.sourceLocation.country}, ${d.sourceLocation.city}`}
                                </Td>
                                <Td>
                                {`${d.destinationLocation.country}, ${d.destinationLocation.city}`}
                                </Td>
                                <Td>
                                   {d.status}
                                </Td>
                                <Td>
                                   {d.transporterUser?.name || '-'}
                                </Td>
                                <Td>
                                   <IconButton icon={<FaTrash />} onClick={() => openModal(d._id)} colorScheme="red" aria-label={`Delete delivery ${d._id}`} />
                                </Td>
                            </Tr>
                        )) : <Text>No deliveries found</Text>}
                    </Tbody>
                </Table>
            </TableContainer>
            <DeleteModal deleteFn={() => deleteDelivery(idToDelete)} entityName="delivery" isOpen={isOpen} onClose={onClose} />
        </Page>
    )
}