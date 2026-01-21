package com.employee.management.system.www.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "reqres")
public class UrlConfigurations {

    @Value("${api.call}")
    private String reqresUrl;
}
