import { Button, Flex, Stack } from '@chakra-ui/react'
import { useContext } from 'react'
import { Link } from 'react-router-dom'
import { AuthContext } from '../auth/AuthContext'

export const Navbar = () => {
    const { user, onLogout } = useContext(AuthContext)

    if (!user) {
        return null
    }

    return (
        <Flex h="4rem" w="full" px={4} py={2} align="center" justify="space-between" borderBottom="1px" borderBottomColor="gray.300">
            <Stack ml={{base: 0, sm: "1rem", md: "3rem", lg: "5rem"}} direction="row" spacing={{base: 1, md: 4}}>
                {user?.isAdmin && (
                    <>
                        <Button
                            as={Link}
                            to="/map"
                            px={2}
                            py={4}
                            variant="ghost"
                            colorScheme="red"
                        >
                            Map
                        </Button>
                        <Button
                            as={Link}
                            to="/deliveries"
                            px={2}
                            py={4}
                            colorScheme="red"
                            variant="ghost"
                        >
                            Deliveries
                        </Button>
                        <Button
                            as={Link}
                            to="/users"
                            px={2}
                            py={4}
                            colorScheme="red"
                            variant="ghost"
                        >
                            Users
                        </Button>
                        <Button
                            as={Link}
                            to="/toplist"
                            px={2}
                            py={4}
                            colorScheme="red"
                            variant="ghost"
                        >
                            Toplist
                        </Button>
                    </>
                )}
            </Stack>
            {user && (
                <Button
                    mr={{base: 0, sm: "1rem", md: "3rem", lg: "5rem"}}
                    px={2}
                    colorScheme="red"
                    py={4}
                    variant="ghost"
                    onClick={onLogout}
                >
                    Logout
                </Button>
            )}
        </Flex>
  )
}