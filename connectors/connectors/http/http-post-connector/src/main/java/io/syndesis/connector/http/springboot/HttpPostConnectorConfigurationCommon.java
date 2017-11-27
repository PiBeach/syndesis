package io.syndesis.connector.http.springboot;

import javax.annotation.Generated;

/**
 * Call a service that is internal (within your company) or external (on the
 * internet) by specifying the service's URL.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.connector.SpringBootAutoConfigurationMojo")
public class HttpPostConnectorConfigurationCommon {

    /**
     * To use either HTTP or HTTPS
     */
    private String scheme = "http";
    /**
     * The hostname of the HTTP server
     */
    private String hostname;
    /**
     * The port number of the HTTP server
     */
    private Integer port;
    /**
     * The context-path
     */
    private String path;
    /**
     * HTTP service to call
     */
    private String serviceName;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}