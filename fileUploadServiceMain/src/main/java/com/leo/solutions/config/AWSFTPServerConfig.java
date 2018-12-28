package com.leo.solutions.config;
/*
 * @author love.bisaria on 24/12/18
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties
@Configuration
public class AWSFTPServerConfig {

    @Value("${aws.ftp.host}")
    private String host;

    @Value("${aws.ftp.port}")
    private Integer port;

    @Value("${aws.ftp.user}")
    private String user;

    @Value("${aws.ftp.certPath}")
    private String certPath;

    @Value("${aws.ftp.certPass}")
    private String certPass;

    @Value("aws.ftp.remotePath")
    private String remotePath;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getCertPath() {
        return certPath;
    }

    public String getCertPass() {
        return certPass;
    }

    public String getRemotePath() {
        return remotePath;
    }
}
