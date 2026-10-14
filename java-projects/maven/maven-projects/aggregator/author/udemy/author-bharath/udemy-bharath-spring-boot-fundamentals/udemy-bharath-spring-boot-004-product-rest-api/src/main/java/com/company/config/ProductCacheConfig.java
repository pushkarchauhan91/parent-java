package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class ProductCacheConfig {
	
	@Bean
	Config cacheConfig() {
		Config config = new Config()
				.setInstanceName("hazel-instance")
				.addMapConfig(new MapConfig()
						.setName("product-cache")
						.setTimeToLiveSeconds(3000));
		// single-node demo: don't search the network for other cluster members
		config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
		return config;
	}
	
	

}
