1, 什么是spring框架, 有哪些主要模块?
> spring框架,就是说我们需要构建一个应用,spring帮我们搭建好了
很多基础设施和框架,让我们能专注于业务开发
> 模块:   
> 有20多个,web层,aop相关,数据访问/集成, 工具,消息,测试模块
> 1.core, context, beans, SpEL
> 2. aop, aspects, messaging
> jdbc, orm,jms, transactions
> web, servlet, websocket, portlet

2, spring如何实现国际化?
> i18n
> 两套参数

3, spring bean的作用域有哪些?
> singleton,
> prototype,
> request, 每次http请求都会创建一个新的bean,
> session, 仅用于http session,同一个session共享一个bean实例
> global session, 仅用于http session

4, spring bean的生命周期?
> 1.bean的建立,
> 2,属性注入
> 3,....
