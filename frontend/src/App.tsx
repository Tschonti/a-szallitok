import { Button, Center, Heading } from '@chakra-ui/react'
import { FaGoogle } from 'react-icons/fa'
import { getAuth, signInWithPopup, GoogleAuthProvider, onAuthStateChanged, User } from 'firebase/auth'
import { app } from './firebase/initApp'
import { useEffect, useState } from 'react'
import axios from 'axios'

const App = () => {
  const [user, setUser] = useState<User | undefined>(undefined)
  const [token, setToken] = useState('')

  useEffect(() => {
    onAuthStateChanged(getAuth(app), (userCred) => {
      if (userCred) {
        setUser(userCred)
        userCred.getIdToken().then((token) => {
          setToken(token)
          axios.get(`${process.env.REACT_APP_BACKEND_URL}/login`, {
            headers: {
              Authorization: 'Bearer ' + token
            }
          })
        })
      }
    })
  }, [])

  const handleLogin = () => {
    signInWithPopup(getAuth(app), new GoogleAuthProvider())
  }

  const sendRequest = () => {

  }

  return (
    <Center>
      {user ?
      <>
        <Heading>Welcome, {user.displayName}!</Heading>
        <Button onClick={sendRequest}>Send request</Button>
      </>
      : <Button leftIcon={<FaGoogle />} colorScheme="red" onClick={handleLogin}>Sign in with Google</Button>}

    </Center>
  );
}

export default App;
