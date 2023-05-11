import axios from 'axios';
import { getToken, removeToken } from '@/utils/cookie.js';
import router from '@/router';
import { ElMessage } from 'element-plus'; 

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: import.meta.env.VITE_BASE_API,
  // 超时
  timeout: 10000
});

// request拦截器
service.interceptors.request.use(config => {
    if (getToken()) {  
      config.headers['Authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    
    return config
  }, error => {
      console.log(error)
      Promise.reject(error)
  })
  
// 响应拦截器
service.interceptors.response.use(res => {
    const status = res.data.status;
    if(status===401) {
      removeToken();
      router.push('/login');
      ElMessage.warning(res.data.result);
    }
    return res;
  },
  error => {
    console.log('err' + error)
    let { message } = error;
    if (message == "Network Error") {
      message = "后端接口连接异常";
    } else if (message.includes("timeout")) {
      message = "系统接口请求超时";
    } else if (message.includes("Request failed with status code")) {
      message = "系统接口" + message.substr(message.length - 3) + "异常";
    }
  //   Message({ message: message, type: 'error', duration: 5 * 1000 })
    return Promise.reject(error)
  }
)

export default service;