# 集成consul

spring cloud consul的服务注册通过autoconfigure来完成，具体代码在

- [org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistrationAutoConfiguration](https://github.com/spring-cloud/spring-cloud-consul/blob/master/spring-cloud-consul-discovery/src/main/java/org/springframework/cloud/consul/serviceregistry/ConsulAutoServiceRegistrationAutoConfiguration.java)

```java
@Bean
@ConditionalOnMissingBean
public ConsulAutoRegistration consulRegistration(ConsulDiscoveryProperties properties, ApplicationContext applicationContext,
        ObjectProvider<List<ConsulRegistrationCustomizer>> registrationCustomizers, HeartbeatProperties heartbeatProperties) {
    return ConsulAutoRegistration.registration(properties, applicationContext, registrationCustomizers.getIfAvailable(), heartbeatProperties);
}
```

ConsulAutoRegistration.registration()方法的实现细节：

```java
public static ConsulAutoRegistration registration(ConsulDiscoveryProperties properties, ApplicationContext context,
        List<ConsulRegistrationCustomizer> registrationCustomizers,
        HeartbeatProperties heartbeatProperties) {
	// 这个NewService是consul中服务定义对应的数据类
    NewService service = new NewService();
    // appName来自配置'spring.cloud.consul.discovery.serviceName'
    String appName = getAppName(properties, context.getEnvironment());
    // instanceId可以通过'spring.cloud.consul.discovery.instanceId'配置
    service.setId(getInstanceId(properties, context));
    if(!properties.isPreferAgentAddress()) {
        service.setAddress(properties.getHostname());
    }
    service.setName(normalizeForDns(appName));
    service.setTags(createTags(properties));

    if (properties.getPort() != null) {
        service.setPort(properties.getPort());
        // we know the port and can set the check
        setCheck(service, properties, context, heartbeatProperties);
    }

    ConsulAutoRegistration registration = new ConsulAutoRegistration(service, properties, context, heartbeatProperties);
    customize(registrationCustomizers, registration);
    return registration;
}
```

服务注册的信息来自bean ConsulDiscoveryProperties，具体实现是类

- [org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties](https://github.com/spring-cloud/spring-cloud-consul/blob/master/spring-cloud-consul-discovery/src/main/java/org/springframework/cloud/consul/discovery/ConsulDiscoveryProperties.java)


| 配置项 | consul服务定义 | ServiceInstance定义 | 说明 ｜
|--------|--------|--------|--------|
|   spring.cloud.consul.discovery.instanceId     |    ID     |    N/A    |    consul内部ID    |
|  N/A    |    ServiceID     |        |   服务实例的唯一ID     |
|   spring.cloud.consul.discovery.serviceName     |    ServiceName     |    serviceId    |    服务名    |
|   spring.cloud.consul.discovery.ipAddress<br>spring.cloud.consul.discovery.hostname     |    ServiceAddress    |   host     |    服务的地址<br>是host还是IP地址取决于preferIpAddress    |
|   spring.cloud.consul.discovery.port     |    ServicePort    |    port    |        |
|   spring.cloud.consul.discovery.tags     |    ServiceTags    |    metadata    |        |
|   spring.cloud.consul.discovery.scheme     |   N/A     |    secure    |    取决于scheme是不是https    |
|   spring.cloud.consul.discovery.scheme     |   N/A     |    scheme    |   新字段还没有开始用     |
|        |        |        |        |
|        |        |        |        |

metadata的映射关系比较复杂，单独把代码拉出来看：

```java
public static Map<String, String> getMetadata(List<String> tags) {
    LinkedHashMap<String, String> metadata = new LinkedHashMap<>();
    if (tags != null) {
        for (String tag : tags) {
        	// 按照等号拆分
            String[] parts = StringUtils.delimitedListToStringArray(tag, "=");
            switch (parts.length) {
                case 0:
                    break;
                case 1:
                	// 没有等号的，则key/value都设置为一样，即"abc"会变成"abc:abc"
                    metadata.put(parts[0], parts[0]);
                    break;
                case 2:
                	// 有一个等号，则"abc=123"会变成"abc:123"
                    metadata.put(parts[0], parts[1]);
                    break;
                default:
                	// 如果有多个等号，则"abc=123=000"会变成"abc:123=000"
                    // 也就是只有第一个等号之前的内容成为key，后面的内容都是value
                    String[] end = Arrays.copyOfRange(parts, 1, parts.length);
                    metadata.put(parts[0], StringUtils.arrayToDelimitedString(end, "="));
                    break;
            }
        }
    }
    return metadata;
}
```

简单的说就是通过等号的使用，实现consul中格式为`List<String>`的tag和spring cloud中格式为`Map<String，String>`的相互转换。


