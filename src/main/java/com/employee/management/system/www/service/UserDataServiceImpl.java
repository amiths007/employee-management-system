package com.employee.management.system.www.service;


import com.employee.management.system.www.config.RestConfig;
import com.employee.management.system.www.config.UrlConfigurations;
import com.employee.management.system.www.model.UserDataResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserDataServiceImpl implements UserDataService {

    private final Logger logger = LoggerFactory.getLogger(UserDataServiceImpl.class);

    private final UrlConfigurations configurations;

    private final RestTemplate restTemplate;

    private final RestConfig restConfig;

    @Cacheable(value = "userData")
    @Override
    public UserDataResponse getUserData() {
        logger.info("Fetching data..");
        try {
            UserDataResponse userDataList = restTemplate.getForObject(configurations.getUrl(), UserDataResponse.class);
            if (Objects.nonNull(userDataList)) {
                return userDataList;
            }

        } catch (Exception e) {
            throw new RestClientException("Error while retrieving Data..!!, Check application logs for details...");
        }

        return null;
    }
}
