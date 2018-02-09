# kubernetes

## 服务定义

kubernetes中的服务定义如下：

- [v1.8](https://v1-9.docs.kubernetes.io/docs/api-reference/v1.8/)
- [v1.9](https://v1-9.docs.kubernetes.io/docs/api-reference/v1.9/#service-v1-core)

| 字段 | 类型 | 描述 | 备注 |
|--------|--------|--------|--------|
|    apiVersion     |    string    |        |   内部实现，和服务注册无关     |
|   kind     |    string    |        |    内部实现，和服务注册无关    |
|   metadata     |    ObjectMeta    |    标准对象元数据    |        |
|    spec    |    ServiceSpec    |    定义服务行为    |        |
|   status     |    ServiceStatus    |    服务最近观察到的状态    |    状态    |


### ObjectMeta

| 字段 | 类型 | 描述 | 备注 | 改动 |
|--------|--------|--------|--------|--------|
|    annotations     |    object    |    key/value map    |    服务携带的数据    | |
|    clusterName     |    string    |    对象所属的cluster的名字    |     服务所在的集群   | 不可修改 |
|    labels     |    object    |    key/value map，可以用来组织和分类对象    |   服务携带的标签     | |
|    name     |    string    |    在namespace内唯一    |    服务名    | 不可修改 |
|    namespace     |    string    |    key/value map    |    服务的namespace    | 不可修改 |
|    annotations     |    object    |    key/value map    |        | 不可修改 |

### ServiceSpec

| 字段 | 类型 | 描述 | 备注 | 改动 |
|--------|--------|--------|--------|--------|
| clusterIP | string | 服务的IP地址 |  | 不可修改 |
| externalIPs | string[] | IP地址列表，对应的集群中的节点可以为这个服务接收流量 |  | |
| externalName | string | 外部引用，kubedns或者类似存在将会返回，作为这个服务的CNAME记录 |  | |
| externalTrafficPolicy | string |  |  | |
| ports | ServicePort[] | 服务暴露的端口列表 |  | |
| selector | object |  |  | |
| type | string | 服务暴露的类型。默认ClusterIP |  | |


### ServicePort

| 字段 | 类型 | 描述 | 备注 |
|--------|--------|--------|--------|
| name | string | 服务端口的名称 |  |
| nodePort | integer | 当type是NodePort或者LoadBalancer时，被暴露的服务所在的node的端口 |  |
| port | integer | 服务暴露的端口 |  |
| protocol | string | 这个端口的IP协议，支持TCP和UDP，默认TCP |  |
| targetPort | integer/string |  |  |




