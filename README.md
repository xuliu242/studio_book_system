## 开放式图书馆预订系统系统 [在线预览](https://app.liujhlx.top/ "在线预览")



![https://img.shields.io/badge/springboot-2.2.1-yellow.svg?style=flat-square](https://img.shields.io/badge/springboot-2.3.7-yellow.svg?style=flat-square)
![https://img.shields.io/badge/shiro-1.4.2-orange.svg?longCache=true&style=flat-square](https://img.shields.io/badge/shiro-1.3.2-orange.svg?longCache=true&style=flat-square)

[后台管理项目](https://github.com/zykzhangyukang/Xinguan "后端项目")
[移动端uni-app项目](https://gitee.com/xuliu242/studio_book_uniapp "前端项目")

#### 简单介绍

>该项目是一个在新冠疫情背景下开发的一个开放式工作室预订管理系统,主要分为系统模块和业务模块.
>
>业务模块是处理疫情期间,学生无法自由出入本校区去往南区图书馆情况下，在教学楼空闲教室预订座位的业务信息；
>系统模块是一个后台通用的RBAC权限模块,可以精确的控制到后台API级别的权限控制；
>开发模式:前后端分离,前端负责数据渲染,后端返回数据。
#### 模块划分

- xinguan-business: 业务模块,所有与业务相关的代码放在此工程中。
- studio_book_system_web: 系统模块,权限控制相关的代码放在此工程中。
- studio_book_system_common: 公共模块,存放工具类、领域模型（DTO）、数据模型对象(DO)一些通用的类。
- xinguan-generator: 代码生成器，生成Controller,Service,ServiceImpl,以及前端代码。
- studio_book_system_view: 前端项目,`npm install`安装依赖后,`npm run serve` 启动该项目。
- xinguan-web: 处理前端请求的Controller,放在此工程中。

#### 技术栈

SpringBoot, Shiro ,Swagger-UI,MyBatis-Plus,JWT,MySQL,Vue.js+element-ui,Nginx,Node.js(v12.9.1版本)

#### 演示页面


- 人员管理

![2](README.assets/studio_user.jpg)


- 权限树

![5](README.assets/studio_menu.jpg)

- 角色分配

![6](README.assets/studio_assign.jpg)


### 联系作者
- 作者QQ

1624742953

### 打赏作者

> 一分一毛也是爱,用于服务器维护. 欢迎大家提意哦见~

