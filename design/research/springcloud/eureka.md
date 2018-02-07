# 集成Eureka

spring cloud eureka的服务注册通过autoconfigure来完成，具体代码在

- [org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration](https://github.com/spring-cloud/spring-cloud-netflix/blob/master/spring-cloud-netflix-eureka-client/src/main/java/org/springframework/cloud/netflix/eureka/EurekaClientAutoConfiguration.java) @ spring-cloud-netflix-eureka-client

```java
@Bean
@ConditionalOnBean(AutoServiceRegistrationProperties.class)
@ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled", matchIfMissing = true)
public EurekaRegistration eurekaRegistration(EurekaClient eurekaClient, CloudEurekaInstanceConfig instanceConfig, ApplicationInfoManager applicationInfoManager) {
    return EurekaRegistration.builder(instanceConfig)
            .with(applicationInfoManager)
            .with(eurekaClient)
            .with(healthCheckHandler)
            .build();
}
```

服务注册的信息来自bean instanceConfig，具体实现是类

- [org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean](https://github.com/spring-cloud/spring-cloud-netflix/blob/master/spring-cloud-netflix-eureka-client/src/main/java/org/springframework/cloud/netflix/eureka/EurekaInstanceConfigBean.java)

```java
@ConfigurationProperties("eureka.instance")
public class EurekaInstanceConfigBean implements CloudEurekaInstanceConfig, EnvironmentAware {}
```

这就回到了我们熟悉的eureka配置信息了，典型如：

- eureka.instance.appname
- eureka.instance.hostname
- eureka.instance.nonSecurePort
- eureka.instance.securePort
- eureka.instance.instanceId
- eureka.instance.ipAddress
- eureka.instance.securePortEnabled
- eureka.instance.metadataMap

eureka配置和spring cloud抽象的ServiceInstance的对应关系：

| eureka配置 | ServiceInstance | 说明 |
|--------|--------|--------|
|    eureka.instance.appname    |    serviceId    |        |
|    eureka.instance.hostname    |    host    |        |
|    eureka.instance.nonSecurePort<br>eureka.instance.securePort    |    port    |    取决于 securePortEnabled    |
|   eureka.instance.securePortEnabled     |    secure    |    boolean    |
|    eureka.instance.metadataMap    |    metadata    |    boolean    |
|   N/A     |    scheme    |    新字段还没有开始用    |

具体映射的代码在

- [org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration](https://github.com/spring-cloud/spring-cloud-netflix/blob/master/spring-cloud-netflix-eureka-client/src/main/java/org/springframework/cloud/netflix/eureka/serviceregistry/EurekaRegistration.java)

参考资料：

- [Spring Cloud Eureka(二) 配置文件](https://blog.tookbra.com/2017/08/24/Spring-Cloud-Eureka-Properties/index.html)