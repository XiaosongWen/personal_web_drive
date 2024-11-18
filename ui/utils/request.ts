// "use client"
// import axios from 'axios'
// import {NextRouter, useRouter} from 'next/router';
//
// const service = axios.create({
//     baseURL: 'http://localhost:8080',
//     timeout: 10000,
//     withCredentials: true,
// })
//
//
//
// service.interceptors.response.use(
//     response => {
//         const res = response.data;
//         if (res.code === 401) {
//             console.log("window", window);
//             console.log("location", window.location);
//             const redirectUrl = encodeURIComponent(window.location.href);  // current URL
//             // router.push(`/login?redirect=${redirectUrl}`);
//             return Promise.reject(new Error(res.message || 'Unauthorized'));
//         }
//         if (res.data?.token){
//             localStorage.setItem("api_token", res.data.token);
//         }
//         return response
//     },
//
//     async error => {
//         return Promise.reject(error)
//     }
// )
// const setupResponseInterceptor = (router: NextRouter) => {
//     service.interceptors.request.use(
//         config => {
//             if (localStorage.getItem("api_token")) {
//                 config.headers.token = localStorage.getItem("api_token");
//             }
//             return config
//         },
//         error => {
//             return Promise.reject(error)
//         }
//     ),
//     service.interceptors.response.use(
//         response => {
//             const res = response.data;
//
//             if (res.code === 401) {
//                 console.log("window", window);
//                 console.log("location", window.location);
//                 const redirectUrl = encodeURIComponent(window.location.href);  // current URL
//                 router.push(`/login?redirect=${redirectUrl}`);
//                 return Promise.reject(new Error(res.message || 'Unauthorized'));
//             }
//
//             if (res.data?.token) {
//                 localStorage.setItem("api_token", res.data.token);
//             }
//
//             return response;
//         },
//         async error => {
//             return Promise.reject(error);
//         }
//     );
// };
// export const useAxiosService = () => {
//     const router = useRouter();
//     setupResponseInterceptor(router);
//     return service;
// };
