package com.example.elasticplugin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;



@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.elasticplugin.repository")
@ComponentScan(basePackages = {"com.example.elasticplugin"})
public class ELSConfig extends ElasticsearchConfiguration {




    @Override
    public ClientConfiguration clientConfiguration() {

        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
    }




}
