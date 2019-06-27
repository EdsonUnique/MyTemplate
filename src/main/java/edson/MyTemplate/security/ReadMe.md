# 安全校验

### 权限拦截

- `MyAuth`类：自定义拦截类；对于用户的相关操作进行权限校验，实现`HandlerInterceptor`接口。
- 同时需要在启动类处对自定义拦截类进行注册，并且指定拦截的路径。




### 异常处理

- `HandlerException`类：对全局的异常进行统一处理。使用`@ControllerAdvice`注解
