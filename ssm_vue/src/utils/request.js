import axios from "axios";

// 创建请求
const request = axios.create({
    timeout: 5000
})

//拦截器
request.interceptors.request.use(config=>{
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config;
},error => {
    return Promise.reject(error);
})

//导出组件
export default request;

// response拦截器
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是文件类型就直接返回
        if(response.config.responseType === 'blob'){
            return res;
        }
        // 如果是字符串就转为json格式
        if(typeof res === 'string'){
            res = res ? JSON.parse(res) : res;
        }
        return res;
    },
    error => {
        return Promise.reject(error);
    }
)
