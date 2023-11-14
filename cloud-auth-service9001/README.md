# 认证模式
参考 https://www.codenong.com/cs107065563/
1. Third-party application: 第三方应用
2. Resource Owner: 资源持有者，一般就是用户自身
3. Authorization server: 认证服务器
4. Resource server: 资源服务器，即具体资源的存储方。与认证服务器是不同的逻辑节点，但是在物理上，双方是可以在一起的
5. User Agent: 用户代理，一般就是指的浏览器
6. Http Service: 服务提供者，也就是持有Resource Server的存在方。可以理解为类似QQ，或者微信这样具备用户信息的服务者

## Spring Security Oauth2 架构

授权服务器：
![oEQ8FU](https://cdn.jsdelivr.net/gh/jaclon-m/Image@main/2023/11/oEQ8FU.png)
Authorize Endpoint：授权端点，进行授权
Token Endpoint：令牌端点，经过授权拿到对应的Token
Introspection Endpoint：校验端点，校验Token的合法性
Revocation Endpoint：撤销端点，撤销授权

![TI63j5](https://cdn.jsdelivr.net/gh/jaclon-m/Image@main/2023/11/TI63j5.png)
流程：

1、用户访问，此时没有Token，Oauth2RestTemplate会报错，这个报错信息会被Oauth2ClientContextFilter捕获并重定向到认证服务器。
2、认证服务器通过Authorization EndPoint进行授权，并通过Authorization·ServerTokenServices生成授权码并返回给客户端。
3、客户端拿到授权码去认证服务器通过Token Endpoint调用AuthorizationServerTokenServices生成Token并返回给客户端。
4、客户端拿到Token去资源服务器访问资源，一般会通过Oauth2AuthenticationManager调用ResourceServerTokenServices进行校验，检验通过可以获取资源。

# 认证流程

![nanKY1](https://cdn.jsdelivr.net/gh/jaclon-m/Image@main/2023/11/nanKY1.png)

# 访问
1. 密码模式
   
```aidl
http://localhost:9001/oauth/token?client_id=user&client_secret=style&grant_type=password&username=user&password=123456
```
返回
```aidl
{
    "access_token": "d8571be3-cb71-42c5-a304-2b1534ec5312",
    "token_type": "bearer",
    "refresh_token": "ced2ca7b-44f9-43a6-8d09-2f585a14983a",
    "expires_in": 1799,
    "scope": "all read write"
}
```
2. 客户端凭证模式
```aidl
http://localhost:9001/oauth/token?client_id=user&client_secret=style&grant_type=client_credentials&scope=read
```
返回
```aidl
{
    "access_token": "d84b20a9-6215-444f-a609-c8a36bd36516",
    "token_type": "bearer",
    "expires_in": 1799,
    "scope": "read"
}
```
3. 授权码模式
浏览器请求
```aidl
http://localhost:9001/oauth/authorize?response_type=code&redirect_uri=https://www.baidu.com&client_id=user&scope=all
```
输入账号密码进行登录
重定向 到 https://www.baidu.com/?code=Zs97m8 并且携带了code码
下一步根据 code 进行获取token
请求地址
```aidl
ttp://localhost:9001/oauth/token?code=Zs97m8&grant_type=authorization_code&redirect_uri=https://www.baidu.com&scope=all&client_id=user&client_secret=style
```
返回
```aidl
{
    "access_token": "af34fe80-2814-41c1-a8f3-13016d57b1ca",
    "token_type": "bearer",
    "refresh_token": "a03f8101-a9dd-44e4-98f0-021d412530bc",
    "expires_in": 1800,
    "scope": "all"
}
```
4. 简化模式
```aidl
http://localhost:9001/oauth/authorize?response_type=token&client_id=user&redirect_uri=https://www.baidu.com
```
首先重定向到登录页面 
登录成功 重定向到设置的重定向地址中
改地址已经存在了 access_token
```aidl
https://www.baidu.com/#access_token=6bf8283f-05dd-4a98-8644-2be03b9888c4&token_type=bearer&expires_in=598&scope=all%20read%20write
```
刷新token 接口
```aidl
http://localhost:9001/oauth/token?client_id=user&client_secret=style&grant_type=refresh_token&refresh_token=a03f8101-a9dd-44e4-98f0-021d412530bc
```
```aidl
{
    "access_token": "6bf8283f-05dd-4a98-8644-2be03b9888c4",
    "token_type": "bearer",
    "refresh_token": "a03f8101-a9dd-44e4-98f0-021d412530bc",
    "expires_in": 1799,
    "scope": "all"
}
```
5. 验证resource
访问http://localhost:9002/port
   
```aidl
{
    "error": "unauthorized",
    "error_description": "Full authentication is required to access this resource"
}
```
带token访问:postman 中authorization -> type:bearer Token -> 输入token
返回正确端口号
```aidl
server port is :9002
```
或者请求头里添加 Authorization value为 bearer{空格}+token

# 参考
实现redis +jwt token
https://www.cnblogs.com/huanshilang/p/15928862.html
实现jwt redis存储；单一客户端登录的企业级example
https://www.codenong.com/cs109735845/