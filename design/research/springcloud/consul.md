# 集成consul

spring cloud consul的服务注册通过autoconfigure来完成，具体代码在

- [org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistrationAutoConfiguration](https://github.com/spring-cloud/spring-cloud-consul/blob/master/spring-cloud-consul-discovery/src/main/java/org/springframework/cloud/consul/serviceregistry/ConsulAutoServiceRegistration.java)

```java
@Bean
@ConditionalOnMissingBean
public ConsulAutoRegistration consulRegistration(ConsulDiscoveryProperties properties, ApplicationContext applicationContext,
        ObjectProvider<List<ConsulRegistrationCustomizer>> registrationCustomizers, HeartbeatProperties heartbeatProperties) {
    return ConsulAutoRegistration.registration(properties, applicationContext, registrationCustomizers.getIfAvailable(), heartbeatProperties);
}
```

服务注册的信息来自bean ConsulDiscoveryProperties，具体实现是类

- [org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties](https://github.com/spring-cloud/spring-cloud-consul/blob/master/spring-cloud-consul-discovery/src/main/java/org/springframework/cloud/consul/discovery/ConsulDiscoveryProperties.java)


| 配置项 | consul服务属性 | springcloud的ServiceInstance| 说明｜
|--------|--------|--------|--------|
|        |    ServiceAddress    |        |    服务的IP地址    |
|        |   ServiceID     |        |   服务实例的唯一ID     |
|        |   ServiceName     |        |    服务名    |
|        |    ServicePort    |        |        |
|        |        |        |        |
|        |        |        |        |
|        |        |        |        |
|        |        |        |        |
|        |        |        |        |





