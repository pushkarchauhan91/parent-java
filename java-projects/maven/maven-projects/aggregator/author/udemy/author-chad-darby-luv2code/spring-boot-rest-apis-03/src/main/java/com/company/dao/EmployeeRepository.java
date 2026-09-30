package com.company.dao;

import com.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // that's it ... no need to write any code LOL!

}
