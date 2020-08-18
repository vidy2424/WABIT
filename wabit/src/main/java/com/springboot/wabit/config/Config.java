//package com.springboot.wabit.config;
//  
// 
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//	@Configuration
//	@EnableJpaRepositories(basePackages = "com.springboot.wabit")
//	@EnableElasticsearchRepositories(basePackages = "com.springboot.wabit.repository")
//	@ComponentScan(basePackages = { "com.springboot.wabit" })
//	//public class Config {
//  	
////	    @Bean
////	    public Client client() throws UnknownHostException {
////	    	Settings settings = Settings.builder()
////	    	         .put("client.transport.sniff", true)
////	         	        .put("cluster.name", "elasticsearch").build();
////	     	@SuppressWarnings("resource")
////			TransportClient client = new PreBuiltTransportClient(settings)
////	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
////	         return client;
////	    }
////	    
////	    @Bean
////	    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
////	        return new ElasticsearchTemplate(client());
////	    }
////	      
//	//}
