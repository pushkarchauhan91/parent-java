package com.bharath.springdata.idgenerators;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bharath.springdata.idgenerators.entities.Employee;
import com.bharath.springdata.idgenerators.repos.EmployeeRepository;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
class IdgeneratorsApplicationTests {

	@Autowired
	EmployeeRepository er;

	@Autowired
	EntityManager em;

	@Test
	void testCreateEmployee() {

		Employee employee = new Employee();
		employee.setName("John");

		Employee saved = er.save(employee);
		// push the insert to the database and drop the cached entity so findById reads it back from MySQL
		em.flush();
		em.clear();

		assertThat(saved.getId()).isNotNull().isBetween(0L, 999999L);
		assertThat(er.findById(saved.getId())).get().extracting(Employee::getName).isEqualTo("John");
	}

}
