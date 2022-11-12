import { Button, Center, Heading } from '@chakra-ui/react'
import axios from 'axios'
import { useContext } from 'react'
import { FaGoogle } from 'react-icons/fa'
import { AuthContext } from './auth/AuthContext'
import { Delivery } from './types/Delivery'

const App = () => {
  const {user, onLogin, onLogout } = useContext(AuthContext)

  const getDeliveries = async () => {
    console.log((await axios.get<Delivery>('/delivery')).data)
  }

  return (
    <Center>
      {user ?
      <>
        <Heading>Welcome, {user.name}!</Heading>
        <Button onClick={onLogout}>Logout</Button>
      </>
      : <Button leftIcon={<FaGoogle />} colorScheme="red" onClick={onLogin}>Sign in with Google</Button>}
      <Button onClick={getDeliveries}>Get deliveries</Button>
    </Center>
  );
}

export default App;
