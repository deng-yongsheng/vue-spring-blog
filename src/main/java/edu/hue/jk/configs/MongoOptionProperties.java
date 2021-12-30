package edu.hue.jk.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据库参数配置
 **/
@Data
@Component
@ConfigurationProperties(prefix = "mongodb.option")
public class MongoOptionProperties {
    private Boolean alwaysUseMBeans = false;
    private Integer connectTimeout = 10000;
    private Integer heartbeatConnectTimeout = 20000;
    private Integer heartbeatSocketTimeout = 20000;
    private Integer localThreshold = 15;
    private Integer maxConnectionIdleTime = 0;
    private Integer maxConnectionLifeTime = 0;
    private Integer maxConnectionsPerHost = 100;
    private Integer minConnectionsPerHost = 0;
    private Integer maxWaitTime = 120000;
    private Integer heartbeatFrequency = 10000;
    private Integer minHeartbeatFrequency = 500;
    private Integer serverSelectionTimeout = 30000;
    private Boolean socketKeepAlive = false;
    private Integer socketTimeout = 0;
    private Boolean sslEnabled = false;
    private Boolean sslInvalidHostNameAllowed = false;
    private Integer threadsAllowedToBlockForConnectionMultiplier = 5;
}
