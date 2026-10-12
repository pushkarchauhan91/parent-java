package com.bharath.springdata.idgenerators.repos;

import com.bharath.springdata.idgenerators.entities.Employee;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
