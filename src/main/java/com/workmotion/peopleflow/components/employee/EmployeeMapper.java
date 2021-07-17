package com.workmotion.peopleflow.components.employee;

import com.workmotion.peopleflow.components.employee.dao.EmployeeEntity;
import com.workmotion.peopleflow.components.employee.dto.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class);

    Employee toEmployee(EmployeeEntity employeeEntity);

    EmployeeEntity toEmployeeEntity(Employee employee);
}
