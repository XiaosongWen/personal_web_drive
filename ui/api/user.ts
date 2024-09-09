import request from '@/utils/request'

// 登录接口
interface LoginBody{
    userEmail: string,
    password: string
}
export const Login = (data:LoginBody) => {
    return  request({
        url: '/api/v1/user/login',
        method: 'post',
        data,
    })
}
export const getUserInfo = () => {
    return  request({
        url: '/api/v1/user/info',
        method: 'get',
    })
}
export const LogOut = () => {
    return  request({
        url: '/api/v1/user/logout',
        method: 'get',
    })
}