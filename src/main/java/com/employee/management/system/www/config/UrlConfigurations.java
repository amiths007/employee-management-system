package com.employee.management.system.www.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "reqres.api")
public class UrlConfigurations {

    private String url;
}
