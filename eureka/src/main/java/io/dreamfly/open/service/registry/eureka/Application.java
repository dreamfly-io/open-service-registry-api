package io.dreamfly.open.service.registry.eureka;

import java.util.List;

public class Application {
    private String name;
    private List<Instance> instances;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
}
