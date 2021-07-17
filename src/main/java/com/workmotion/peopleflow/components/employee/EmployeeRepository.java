package com.workmotion.peopleflow.components.employee;

import java.util.Optional;
import com.workmotion.peopleflow.components.employee.dao.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
@Repository
interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByPhoneNumber(String phoneNumber);

}
