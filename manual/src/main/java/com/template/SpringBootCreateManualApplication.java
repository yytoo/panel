package com.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 * @Configuration 将当前类标记为一个配置类，允许在该类中定义 Bean 或导入其他配置类。
 * @EnableAutoConfiguration 启用 Spring Boot 的自动配置机制。根据项目中的依赖库自动配置 Spring 上下文，例如如果你添加了 spring-boot-starter-web 依赖，Spring Boot 会自动配置嵌入式的 Tomcat 容器和 Spring MVC。
 * @ComponentScan 自动扫描并注册带有 @Component, @Service, @Repository, @Controller 等注解的类为 Spring 管理的 Bean。默认情况下，它会从声明 @SpringBootApplication 的类所在的包开始递归扫描。
 * exclude参数常用于排除一些自动配置
 * 此时执行的任务:
 *      解析 @Configuration：将当前类视为一个配置类，可能包含 Bean 定义方法或 @Bean 方法。
 *      启用自动配置：根据项目的依赖关系查找并应用相应的自动配置类。
 *      执行组件扫描：从启动类所在包及其子包中寻找带有 Spring 组件注解的类，并将它们注册为 Spring 管理的 Bean。
 *      加载外部配置：读取 application.properties 或 application.yml 文件中的配置项，并将其注入到应用程序上下文中。
 *      发布事件：在整个启动过程中发布一系列事件，允许监听器对这些事件做出响应。
 */
@SpringBootApplication
@MapperScan("com.template.manual.mapper")
public class SpringBootCreateManualApplication
{
    public static void main( String[] args )
    {
        /**
         * 1. 创建 SpringApplication 实例
         *     SpringApplication app = new SpringApplication(MyApplication.class);
         *     app.run(args);
         *2. 准备环境 (Environment)
         *      SpringApplication 会根据命令行参数、系统属性、配置文件等信息准备 Environment 对象。
         *      Environment 包含了所有的外部配置数据，如属性文件 (application.properties 或 application.yml) 中定义的值。
         * 3. 解析并加载配置类
         *      Spring Boot 会自动扫描主类及其包下的所有组件（包括控制器、服务、存储库等），并将它们注册到应用上下文中。
         *      此外，还会处理 @Configuration 类以及通过 @ComponentScan 指定的额外包。
         * 4. 初始化和配置嵌入式服务器（如果适用）
         *      对于基于Web的应用程序，默认情况下会初始化一个嵌入式的Servlet容器（如Tomcat、Jetty或Undertow）。
         *      你可以通过设置 server.port 等属性来自定义服务器配置。
         * 5. 创建并刷新应用上下文 (ApplicationContext)
         *      SpringApplication 创建了一个适合当前运行模式的应用上下文（例如 AnnotationConfigServletWebServerApplicationContext 对于Web应用）
         *      5.1 加载所有Bean定义。
         *      5.2 初始化所有单例Bean。
         *      5.3 触发Bean的生命周期回调（如 InitializingBean.afterPropertiesSet() 和 @PostConstruct 注解的方法）。
         *      5.4 执行任何监听器和事件发布机制（如 ApplicationListener 和 SmartLifecycle 接口实现）。
         *      5.4 执行任何监听器和事件发布机制（如 ApplicationListener 和 SmartLifecycle 接口实现）。
         * 6. 发布应用程序事件
         *      在整个启动过程中，SpringApplication 会发布多个事件给已注册的监听器
         *      ApplicationStartingEvent/ApplicationEnvironmentPreparedEvent/ApplicationPreparedEvent/ApplicationStartedEvent/ApplicationReadyEvent
         *      这些事件可以被用来执行自定义逻辑，例如记录日志、预热缓存等。
         * 7. 启动完成
         *      当所有必要的初始化工作完成后，SpringApplication.run 返回一个 ConfigurableApplicationContext 实例，表示应用程序已经成功启动并且可以开始接收请求了。
         * 8. 优雅关闭
         *      虽然不是 run 方法的一部分，但值得注意的是，当应用程序需要关闭时（例如接收到SIGTERM信号），Spring Boot 提供了一套优雅的关闭机制。它会触发 ContextClosedEvent 并调用所有实现了 DisposableBean 接口或带有 @PreDestroy 注解的方法，确保资源被正确释放。
         */
        SpringApplication.run(SpringBootCreateManualApplication.class, args);
        System.out.println( "Hello World!" );
    }
}
