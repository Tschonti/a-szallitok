import axios from "axios"
import Cookies from "js-cookie"
import { BASE_URL, COOKIE_KEY_JWT } from "./Constants"

export const initAxios = () => {
    axios.defaults.baseURL = BASE_URL
    axios.interceptors.request.use((config) => {
      const token = Cookies.get(COOKIE_KEY_JWT)
      if (token && config.headers) {
        config.headers['Authorization'] = `Bearer ${token}`
      }
      return config
    })
  }