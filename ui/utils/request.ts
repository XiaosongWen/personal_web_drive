"use client"
import axios from 'axios'

const service = axios.create({
    baseURL: 'http://localhost:8888',
    timeout: 10000,
    withCredentials: true,
})

service.interceptors.response.use(
    response => {
        const res = response.data;
        if (res.code === 401) {
            return Promise.reject(new Error(res.message || 'Unauthorized'));
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
export default service;

