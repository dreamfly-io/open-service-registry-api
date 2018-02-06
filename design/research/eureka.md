# Euraka

## 服务定义

Euraka中对服务的定义体现在这个类中：

- [com.netflix.appinfo.InstanceInfo](https://github.com/Netflix/eureka/blob/master/eureka-client/src/main/java/com/netflix/appinfo/InstanceInfo.java) @eureka-client

| 参数 | 类型 | 说明 |
|--------|--------|--------|
|    instanceId    |    String    |   The (fixed) instanceId for this instanceInfo. <br>This should be unique within the scope of the appName.  |
|    appName    |    String    |        |
|    appGroupName    |    String    |        |
|    ipAddr    |    String    |        |
|    port    |    String    |        |
|    securePort    |    String    |        |
|    countryId    |    int    |    @Deprecated    |
|    isSecurePortEnabled    |    false    |    默认false    |
|    isUnsecurePortEnabled    |    false    |    默认true    |
|    dataCenterInfo    |    DataCenterInfo    |        |
|    hostName    |    String    |        |
|    status    |    InstanceStatus    |        |
|    overriddenstatus    |    InstanceStatus    |        |
|    metadata    |    `Map<String, String>`    |        |
|    version    |    String    |        |

从数据结构上看，eureka支持多数据中心，支持版本。

### 服务状态

`InstanceInfo.InstanceStatus`枚举类型用来描述服务状态：

| 状态 | 说明 |
|--------|--------|
|    UP    |    准备好接收请求   |
|    DOWN    |    不要发请求- 健康检查失败    |
|    STARTING    |    正在启动中<br>- 初始化还未完成 - <br>不要发请求    |
|    OUT_OF_SERVICE    |    明确关闭不接受请求    |
|    UNKNOWN    |    未知    |

### 数据中心

[com.netflix.appinfo.DataCenterInfo](https://github.com/Netflix/eureka/blob/master/eureka-client/src/main/java/com/netflix/appinfo/DataCenterInfo.java)接口用来描述数据中心信息：

```java
public interface DataCenterInfo {
    enum Name {Netflix, Amazon, MyOwn}

    Name getName();
}
```

具体实现类：

- [com.netflix.appinfo.AmazonInfo](https://github.com/Netflix/eureka/blob/master/eureka-client/src/main/java/com/netflix/appinfo/AmazonInfo.java)
- [com.netflix.appinfo.MyDataCenterInfo](https://github.com/Netflix/eureka/blob/master/eureka-client/src/main/java/com/netflix/appinfo/MyDataCenterInfo.java)

> 备注：比较有意思的是，AmazonInfo在实现时，往metadata里面放了一堆数据，倒是和metadata的设计初衷符合：的确是有些信息适合附带在服务注册信息中。

