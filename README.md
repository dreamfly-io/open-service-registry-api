# Open API for service registry and discovery

Open Service Registry API 项目致力于提供一整套的服务注册相关的API，以及多种底层实现。

目标在于为上层应用提供一个实现无关的抽象层，避免上层应用直接和底层服务注册机制耦合。

同时提供在多个底层实现选择和切换的灵活性。

目前计划支持的底层实现有：

- etcd3： 重点，开发进行中
- k8s: 重点，开发进行中
- ~~consul： 计划中，低优先级~~
- ~~zookeeper： 计划中，低优先级~~
- In-Memory: 计划中，用于测试
