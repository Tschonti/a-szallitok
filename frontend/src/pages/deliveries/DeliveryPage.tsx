import { Heading, IconButton, Table, TableContainer, Tbody, Td, Th, Thead, Tr, useToast } from "@chakra-ui/react"
import axios from "axios"
import { useEffect, useState } from "react"
import { FaTrash } from "react-icons/fa"
import { Page } from "../../components/Page"
import { Delivery } from "../../types/Delivery"
import { getErrorMessage } from "../../util/errorMessage"

export const DeliveryPage = () => {
    const toast = useToast()
    const [deliveries, setDeliveries] = useState<Delivery[] | undefined>(undefined)

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
                <Table variant="striped" colorScheme="red">
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
                        {deliveries && deliveries.map(d => (
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
                                   <IconButton icon={<FaTrash />} onClick={() => deleteDelivery(d._id)} colorScheme="red" aria-label={`Delete delivery ${d._id}`} />
                                </Td>
                            </Tr>
                        ))}
                    </Tbody>
                </Table>

            </TableContainer>
        </Page>
    )
}