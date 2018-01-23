# Open API for service registry and discovery

![build](https://travis-ci.org/openfoundation/open-service-registry-api.svg?branch=master)

## English

Open Service Registry API project is committed to providing a complete set of service registration API and multiple implementations.

Our target is to provide an implementation-independent abstraction layer for the upper applications, and to avoid that the applications  are directly coupled with the underlying service registration mechanism。

At the same time, it will provide the flexibility to select and switch between multiple implementations.

Following underlying implementations are planed to be supported:

- etcd3： main focus，under developing
- ~~consul： in plan, not started~~
- ~~zookeeper： in plan, not started~~
- In-Memory: in plan, only for test

## 中文

Open Service Registry API 项目致力于提供一整套的服务注册相关的API，以及多种底层实现。

目标在于为上层应用提供一个实现无关的抽象层，避免上层应用直接和底层服务注册机制耦合。

同时提供在多个底层实现选择和切换的灵活性。

目前计划支持的底层实现有：

- etcd3： 重点，开发进行中
- ~~consul： 计划中~~
- ~~zookeeper： 计划中~~
- In-Memory: 计划中，用于测试
