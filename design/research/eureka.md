# Euraka

## 服务定义

Spring Cloud中对服务的定义体现在下面两个类中：

- com.netflix.appinfo.InstanceInfo

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


### 服务状态

| 状态 | 说明 |
|--------|--------|
|    UP    |    Ready to receive traffic    |
|    DOWN    |    Do not send traffic- healthcheck callback failed    |
|    STARTING    |    Just about starting<br>- initializations to be done - <br>do not send traffic    |
|    OUT_OF_SERVICE    |    Intentionally shutdown for traffic    |
|    UNKNOWN    |    未知    |

### 数据中心

`com.netflix.appinfo.DataCenterInfo`接口用来描述数据中心信息：

```java
public interface DataCenterInfo {
    enum Name {Netflix, Amazon, MyOwn}

    Name getName();
}
```

具体实现类：

- com.netflix.appinfo.AmazonInfo
- com.netflix.appinfo.MyDataCenterInfo

> 备注：比较有意思的是，AmazonInfo在实现时，往metadata里面放了一堆数据，倒是和metadata的设计初衷符合：的确是有些信息适合附带在服务注册信息中。

