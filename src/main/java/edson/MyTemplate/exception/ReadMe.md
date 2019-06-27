# 异常类

- 自定义异常，一般是运行时异常`RuntimeException`。
- 异常类型的枚举类（`MyExceptionEnum`）：定义不同类型的异常（项目相关如相关状态异常等）；包括异常码和异常信息。

- 自定义异常类：继承`RuntimeException`，只需调用父类的`super(message)`方法构造实例。为区分不同类型的异常，定义类型码。


