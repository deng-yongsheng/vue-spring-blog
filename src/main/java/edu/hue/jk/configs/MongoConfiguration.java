package edu.hue.jk.configs;

import com.mongodb.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * mongo配置
 **/
@Setter
@Configuration
@ConfigurationProperties(prefix = "mongodb.dbconfig")
public class MongoConfiguration {

    private String database;
    private String username;
    private String password;
    private String host;
    private Integer port;


    @Autowired
    private MongoOptionProperties mongoOptionProperties;

    /**
     * mongo工厂
     *
     * @return org.springframework.data.mongodb.MongoDbFactory
     */
    @Bean
    public MongoDbFactory mongoDbFactory() {
        //==========客户端配置==========
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(mongoOptionProperties.getMaxConnectionsPerHost());
        builder.minConnectionsPerHost(mongoOptionProperties.getMinConnectionsPerHost());
        builder.threadsAllowedToBlockForConnectionMultiplier(mongoOptionProperties.getThreadsAllowedToBlockForConnectionMultiplier());
        builder.serverSelectionTimeout(mongoOptionProperties.getServerSelectionTimeout());
        builder.maxWaitTime(mongoOptionProperties.getMaxWaitTime());
        builder.maxConnectionIdleTime(mongoOptionProperties.getMaxConnectionIdleTime());
        builder.maxConnectionLifeTime(mongoOptionProperties.getMaxConnectionLifeTime());
        builder.connectTimeout(mongoOptionProperties.getConnectTimeout());
        builder.socketTimeout(mongoOptionProperties.getSocketTimeout());
        builder.socketKeepAlive(mongoOptionProperties.getSocketKeepAlive());
        builder.sslEnabled(mongoOptionProperties.getSslEnabled());
        builder.sslInvalidHostNameAllowed(mongoOptionProperties.getSslInvalidHostNameAllowed());
        builder.alwaysUseMBeans(mongoOptionProperties.getAlwaysUseMBeans());
        builder.heartbeatFrequency(mongoOptionProperties.getHeartbeatFrequency());
        builder.minHeartbeatFrequency(mongoOptionProperties.getMinHeartbeatFrequency());
        builder.heartbeatConnectTimeout(mongoOptionProperties.getHeartbeatConnectTimeout());
        builder.heartbeatSocketTimeout(mongoOptionProperties.getHeartbeatSocketTimeout());
        builder.localThreshold(mongoOptionProperties.getLocalThreshold());
        MongoClientOptions mongoClientOptions = builder.build();

        ServerAddress serverAddress = new ServerAddress(host, port);

        MongoClient mongoClient;
        if (username != null && password != null) {
            // 连接认证
            MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(username, database, password.toCharArray());
            //
            mongoClient = new MongoClient(serverAddress, mongoCredential, mongoClientOptions);
        } else {
            // 使用无密码验证
            mongoClient = new MongoClient(serverAddress, mongoClientOptions);
        }
        // 创建MongoDbFactory
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, database);
        return mongoDbFactory;
    }

    /**
     * 默认mongo句柄（写关注为SAFE）
     *
     * @param mongoDbFactory
     * @return org.springframework.data.mongodb.core.MongoTemplate
     */
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        mongoTemplate.setWriteConcern(WriteConcern.SAFE);
        return mongoTemplate;
    }
}
