const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    port: 8081,
    proxy: {
      '/Fur': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: { //路径重写
          '/Fur': '' //选择忽略拦截器里面的单词
        }
      }
    }
  }
})
