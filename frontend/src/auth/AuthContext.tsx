import { signInWithPopup, getAuth, GoogleAuthProvider } from "firebase/auth"
import { createContext, useState } from "react"
import Cookies from 'js-cookie'
import { User } from "../types/User"
import { HasChildren } from "../types/Utils"
import { app } from "./initFirebaseApp"
import { COOKIE_KEY_JWT, } from "../util/Constants"
import axios from "axios"
import { useNavigate } from "react-router-dom"

export type AuthContextType = {
    user: User | undefined
    onLogout: () => void
    onLogin: () => void
}

export const AuthContext = createContext<AuthContextType>({
    user: undefined,
    onLogin: () => {},
    onLogout: () => {}
})

export const AuthProvider = ({children}: HasChildren) => {
    const [user, setUser] = useState<User | undefined>()
    const navigate = useNavigate()

    const onLogin = async () => {
        const userCred = await signInWithPopup(getAuth(app), new GoogleAuthProvider())
        Cookies.set(COOKIE_KEY_JWT, await userCred.user.getIdToken())
        const res =  await axios.get<User>('/login')
        setUser(res.data)
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
                onLogout
            }}
        >
            { children }
        </AuthContext.Provider>
    )
}