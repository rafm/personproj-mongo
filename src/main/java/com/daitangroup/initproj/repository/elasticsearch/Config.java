package com.daitangroup.initproj.repository.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class Config {

	@Value("${elasticsearch.clusterName:elasticsearch}")
	private String clusterName;

	@Value("${elasticsearch.host:localhost}")
	private String host;
	
	@Value("${elasticsearch.port:9300}")
	private int port;
	
	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
		TransportClient transportClient = new PreBuiltTransportClient(Settings.builder().put("cluster.name", clusterName).build());
		transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
		return new ElasticsearchTemplate(transportClient);
	}
}