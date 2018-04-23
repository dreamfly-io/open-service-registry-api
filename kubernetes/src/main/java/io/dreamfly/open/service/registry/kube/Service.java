package io.dreamfly.open.service.registry.kube;

import java.util.List;

public class Service {
    private String ip;
    private String name;
    private String namespace;
    private List<String> labels;
    private List<String> annotations;
    private List<NamedPort> namePorts;
}
