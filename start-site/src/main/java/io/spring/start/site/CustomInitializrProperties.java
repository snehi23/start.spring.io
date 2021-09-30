package io.spring.start.site;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import io.spring.initializr.metadata.InitializrProperties;
import lombok.Data;

@Data
@ConfigurationProperties("custom")
public class CustomInitializrProperties {

  @NestedConfigurationProperty
  InitializrProperties initializr = new InitializrProperties();

}
