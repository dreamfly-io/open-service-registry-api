# Spring Cloud服务定义

## 服务定义

Spring Cloud中对服务的定义体现在下面两个类中：

- org.springframework.cloud.client.ServiceInstance
- org.springframework.cloud.client.DefaultServiceInstance

参数不多：

| 参数 | 类型 | 说明 |
|--------|--------|--------|
|    serviceId    |    String    |   service id，由DiscoveryClient设置     |
|    host    |    String    |    主机名    |
|    port    |    int    |    端口    |
|    secure    |    boolean    |    是否加密，简单说就是是否https    |
|    metadata    |    `Map<String, String>`    |    元数据    |


spring cloud只支持rest/http1.1，所以有些东西都是写死的，比如getUri()方法：

```bash
public static URI getUri(ServiceInstance instance) {
	// 直接写死了http和https
    String scheme = (instance.isSecure()) ? "https" : "http";
    String uri = String.format("%s://%s:%s", scheme, instance.getHost(), instance.getPort());
    return URI.create(uri);
}
```

## 实现

### Eureka实现

### ZooKeeper实现

### Consul实现


