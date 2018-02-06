# Spring Cloud

## 服务定义

Spring Cloud中对服务的定义体现在下面两个类中：

- [org.springframework.cloud.client.ServiceInstance](https://github.com/spring-cloud/spring-cloud-commons/blob/master/spring-cloud-commons/src/main/java/org/springframework/cloud/client/ServiceInstance.java)
- [org.springframework.cloud.client.DefaultServiceInstance](https://github.com/spring-cloud/spring-cloud-commons/blob/master/spring-cloud-commons/src/main/java/org/springframework/cloud/client/DefaultServiceInstance.java)

参数不多：

| 参数 | 类型 | 说明 |
|--------|--------|--------|
|    serviceId    |    String    |   service id，由DiscoveryClient设置     |
|    host    |    String    |    主机名    |
|    port    |    int    |    端口    |
|    secure    |    boolean    |    是否加密，简单说就是是否https    |
|    metadata    |    `Map<String, String>`    |    元数据    |
|    scheme    |    String    |    **在2018年1月才新加入的**    |

spring cloud只支持rest/http1.1，所以有些信息是写死的，比如getUri()方法中获取scheme：

```bash
public static URI getUri(ServiceInstance instance) {
	// 直接写死了http和https
    String scheme = (instance.isSecure()) ? "https" : "http";
    String uri = String.format("%s://%s:%s", scheme, instance.getHost(), instance.getPort());
    return URI.create(uri);
}
```

更新：

在2018年1月，增加了scheme这个参数，检查了一下DefaultServiceInstance暂时还没有对应的改动，继续观察中。

> 注：不确认这是否意味着spring cloud要支持http之外的其他协议。

## 接口定义

### 客户端接口

- [org.springframework.cloud.client.discovery.DiscoveryClient](https://github.com/spring-cloud/spring-cloud-commons/blob/master/spring-cloud-commons/src/main/java/org/springframework/cloud/client/discovery/DiscoveryClient.java) @ spring-cloud-commons

代码实现：

```java
public interface DiscoveryClient {

	/**
	 * 实现可读的描述，用于HealthIndicator
	 */
	String description();

	/**
	 * 获取和指定serviceId关联的所有服务实例
	 */
	List<ServiceInstance> getInstances(String serviceId);

	/**
	 * 返回所有已知的服务id。（注意只是id）
	 */
	List<String> getServices();
}
```

从接口上看，服务发现实现的非常简单，甚至简陋：

- 参数只有一个serviceId: 没有group，datacenter，version
- 意味着没有任何参数可以用于过滤，比如选择特定的version/datacenter
- 服务状态既不在请求参数中，也不在返回数据中：这意味着只能默认查找可用状态，返回的也只能是可用状态的实例
- 没有监听方法： 那如何获取到服务的变动？只能交给具体实现。

### 服务器端接口

服务器端用于服务注册的数据类接口定义是：

- [org.springframework.cloud.client.serviceregistry.Registration](https://github.com/spring-cloud/spring-cloud-commons/blob/master/spring-cloud-commons/src/main/java/org/springframework/cloud/client/serviceregistry/Registration.java)

Registration开始是一个独立接口：

```java
public interface Registration {
	String getServiceId();
}
```

最新的版本(2017年10月之后)中，修改为继承自客户端的ServiceInstance接口，这样客户端和服务器端使用的服务定义就一致了：

```java
public interface Registration extends ServiceInstance {}
```

服务注册的接口定义：

- [org.springframework.cloud.client.serviceregistry.ServiceRegistry](https://github.com/spring-cloud/spring-cloud-commons/blob/master/spring-cloud-commons/src/main/java/org/springframework/cloud/client/serviceregistry/ServiceRegistry.java)

代码实现：

```java
public interface ServiceRegistry<R extends Registration> {

	/**
	 * 注册
	 */
	void register(R registration);

	/**
	 * 注销
	 */
	void deregister(R registration);

	/**
	 * 关闭服务注册，这是一个生命周期方法。
	 */
	void close();

	/**
	 * 设置状态。状态值由不同的实现来决定。
	 */
	void setStatus(R registration, String status);

	/**
	 * 获取状态
	 */
	<T> T getStatus(R registration);
}
```

服务器端的接口可以通过setStatus()/getStatus()来获取和修改实例的状态。

## 具体实现

### Eureka实现


### ZooKeeper实现

### Consul实现


