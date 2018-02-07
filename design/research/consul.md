# consul

consul的服务定义可以参考官方文档：

- [Service Definition](https://www.consul.io/docs/agent/services.html)
- [Catalog HTTP API](https://www.consul.io/api/catalog.html)

这是一个典型例子，返回的是一个服务的注册信息：

```javascript
{
    "ID": "40e4a748-2192-161a-0510-9bf59fe950b5",
    "Node": "foobar",
    "Address": "192.168.10.10",
    "Datacenter": "dc1",
    "TaggedAddresses": {
      "lan": "192.168.10.10",
      "wan": "10.0.10.10"
    },
    "NodeMeta": {
      "somekey": "somevalue"
    },
    "CreateIndex": 51,
    "ModifyIndex": 51,
    "ServiceAddress": "172.17.0.3",
    "ServiceEnableTagOverride": false,
    "ServiceID": "32a2a47f7992:nodea:5000",
    "ServiceName": "foobar",
    "ServicePort": 5000,
    "ServiceTags": [
      "tacos"
    ]
}
```

详细的字段解释：

| consul字段 |  说明 |
|--------|--------|
|    ID    |      consul内部id    |
|   Node     |       服务注册所在的consul node   |
| Address | 服务注册所在的consul node的IP地址 |
|   Datacenter     |       服务注册所在的Datacenter    |
|   TaggedAddresses     |   agent的LAN and WAN地址     |
|    NodeMeta     |   用户定义的元数据键值对，用于node     |
|    CreateIndex    |   表示服务何时创建的内部值     |
|    ModifyIndex     |   表示服务最后修改的内部值     |
|   ServiceAddress     |    服务所在的IP地址，如果为空则使用    |
|    ServiceEnableTagOverride    |    标明这个服务上的tag是否可以被覆盖    |
|   ServiceID     |    服务实例的唯一ID    |
|   ServiceName     |    服务名    |
|   ServicePort     |    服务端口号    |
|   ServiceTags     |    服务的tag列表    |

consul的服务注册是支持多数据中心的，这是一个很大的天然优势。

注意ServiceTags只是一个简单的string[]，而不是`map<String, String>`这样的一个map。换句话说，consul的tag只有key，没有value。后面和spring cloud集成时，会使用一个简单的技巧，通过在tag中使用等号来将一个string("key=value"格式)转为一对key/value。

