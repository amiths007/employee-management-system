package com.employee.management.system.www.service;


import com.employee.management.system.www.config.RestConfig;
import com.employee.management.system.www.config.UrlConfigurations;
import com.employee.management.system.www.model.UserDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final Logger logger = LoggerFactory.getLogger(UserDataServiceImpl.class);

    @Autowired
    private UrlConfigurations configurations;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestConfig restConfig;

    @Override
    public UserDataResponse getUserData() {
        logger.info("Fetching data..");
        try {
            UserDataResponse userDataList = restTemplate.getForObject(configurations.getReqresUrl(), UserDataResponse.class);
            if (Objects.nonNull(userDataList)) {
                return userDataList;
            }

        } catch (Exception e) {
            throw new RestClientException("Error while retrieving Data..!!, Check application logs for details...");
        }

        return null;
    }
}
