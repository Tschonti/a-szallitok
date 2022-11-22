import { signInWithPopup, getAuth, GoogleAuthProvider } from "firebase/auth"
import { createContext, useEffect, useState } from "react"
import Cookies from 'js-cookie'
import { User } from "../types/User"
import { HasChildren } from "../types/Utils"
import { app } from "./initFirebaseApp"
import { COOKIE_KEY_JWT, } from "../util/Constants"
import axios from "axios"
import { useNavigate } from "react-router-dom"
import { useToast } from "@chakra-ui/react"
import { getErrorMessage } from "../util/errorMessage"

export type AuthContextType = {
    user: User | undefined
    onLogout: () => void
    onLogin: () => void
    loading: boolean
}

export const AuthContext = createContext<AuthContextType>({
    user: undefined,
    onLogin: () => {},
    onLogout: () => {},
    loading: false
})

export const AuthProvider = ({children}: HasChildren) => {
    const [user, setUser] = useState<User | undefined>()
    const [loading, setLoading] = useState(false)
    const navigate = useNavigate()
    const toast = useToast()

    useEffect(() => {
        const login = async () => {
            const res =  await axios.get<User>('/login')
            setUser(res.data)
        }
        if (Cookies.get(COOKIE_KEY_JWT)) {
            login().catch(console.error)
        }
    }, [])

    const onLogin = async () => {
        const userCred = await signInWithPopup(getAuth(app), new GoogleAuthProvider())
        setLoading(true)
        Cookies.set(COOKIE_KEY_JWT, await userCred.user.getIdToken())
        try {
            const res =  await axios.get<User>('/login')
            setUser(res.data)
            setLoading(false)
        } catch (e) {
            toast({
                title: 'Failed to log in',
                description: getErrorMessage(e),
                duration: 5000,
                status: 'error'
            })
            onLogout()
        }
    }

    const onLogout = async () => {
        Cookies.remove(COOKIE_KEY_JWT)
        setUser(undefined)
        navigate('/')
    }

    return (
        <AuthContext.Provider
            value={{
                user,
                onLogin,
                onLogout,
                loading
            }}
        >
            { children }
        </AuthContext.Provider>
    )
}