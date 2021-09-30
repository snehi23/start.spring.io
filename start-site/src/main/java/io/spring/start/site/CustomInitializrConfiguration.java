package io.spring.start.site;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.metadata.InitializrMetadataBuilder;
import io.spring.initializr.metadata.InitializrProperties;
import io.spring.initializr.web.support.DefaultInitializrMetadataProvider;
import io.spring.initializr.web.support.InitializrMetadataUpdateStrategy;

@Configuration
@EnableConfigurationProperties(CustomInitializrProperties.class)
public class CustomInitializrConfiguration {

	@Bean
	public DefaultInitializrMetadataProvider customInitializrMetadataProvider(InitializrProperties initializrProperties,
			CustomInitializrProperties customInitializrProperties,
			InitializrMetadataUpdateStrategy initializrMetadataUpdateStrategy) {
		InitializrMetadata meta = InitializrMetadataBuilder
				.fromInitializrProperties(customInitializrProperties.initializr)
				.withInitializrProperties(initializrProperties, true).build();
		return new DefaultInitializrMetadataProvider(meta, initializrMetadataUpdateStrategy);
	}

}
