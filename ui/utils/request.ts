import axios from 'axios'
import {router} from "next/client";
// import router from '@/router'
// import { useApp } from '@/pinia/modules/app'

export interface Respond {
    code: number,
    message: string,
    data: object
}
const service = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10000,
    withCredentials: true,
})

service.interceptors.request.use(
    config => {
        if (localStorage.getItem("api_token")) {
            config.headers.token = localStorage.getItem("api_token");
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

service.interceptors.response.use(
    response => {
        const res = response.data;
        if (res.code === 208) {
            const redirect = encodeURIComponent(window.location.href)  // 当前地址栏的url
            router.push(`/login?redirect=${redirect}`)
            return Promise.reject(new Error(res.message || 'Error'))
        }
        if (res.data?.token){
            localStorage.setItem("api_token", res.data.token);
        }
        return response
    },

    async error => {
        return Promise.reject(error)
    }
)

export default service