package io.dreamfly.open.service.registry.eureka;

import java.util.Map;

public class Instance {
    private String hostName;
    private String pPAddress;
    private String Status;

    private Map<String,String> metadata;
    private Port securePort;
    private Port port;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getpPAddress() {
        return pPAddress;
    }

    public void setpPAddress(String pPAddress) {
        this.pPAddress = pPAddress;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Port getSecurePort() {
        return securePort;
    }

    public void setSecurePort(Port securePort) {
        this.securePort = securePort;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
