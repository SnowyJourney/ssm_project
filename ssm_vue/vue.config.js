const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      '/Fur': {
            target: 'http://192.168.31.122:8081',
            changeOrigin: true,
      }
    }
  }
}