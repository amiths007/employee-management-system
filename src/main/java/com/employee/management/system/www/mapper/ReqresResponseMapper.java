package com.employee.management.system.www.mapper;


import com.employee.management.system.www.model.Employee;
import com.employee.management.system.www.model.UserData;
import com.employee.management.system.www.model.UserDataResponse;
import com.employee.management.system.www.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ReqresResponseMapper {

    private final Logger logger = LoggerFactory.getLogger(ReqresResponseMapper.class);

    public List<Employee> userDataResponseMapper(UserDataResponse src, List<Employee> dest){

        logger.debug("Entering Reqres response mapper...");
        if (Objects.isNull(src.getUserDataList())){
            return new ArrayList<>();
        }
        if (!Objects.isNull(dest) && !CollectionUtils.isEmpty(dest)){
            for (Employee employee : dest) {
                if (Objects.nonNull(employee)) {
                    for (UserData userData : src.getUserDataList()) {
                        if (userData.getEmail().equalsIgnoreCase(employee.getEmail())) {
                            employee.setAvatar(userData.getAvatar());
                            break;
                        }
                    }
                }
            }
        }
        return dest;
    }


}
