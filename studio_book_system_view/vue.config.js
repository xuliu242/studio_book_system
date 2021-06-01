module.exports = {
  lintOnSave: false,
  runtimeCompiler: true,
  publicPath: "/", // 设置打包文件相对路径
  devServer: {
    // open: process.platform === 'darwin',
    // host: 'localhost',
    port: 3000,
    // open: true, //配置自动启动浏览器
    proxy: {
      "/studio": {
        target: "http://127.0.0.1:8088/", //对应自己的接口
        // target: 'http://123.56.235.185:8088',
        changeOrigin: true,
        ws: true
        // pathRewrite: {
        //   '^/daka': ''
        // }
      }
    }
  }
};
