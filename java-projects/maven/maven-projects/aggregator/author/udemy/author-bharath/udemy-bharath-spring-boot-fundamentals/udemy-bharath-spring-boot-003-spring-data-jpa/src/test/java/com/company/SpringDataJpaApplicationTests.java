package com.company;

import com.company.entity.Student;
import com.company.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringDataJpaApplicationTests {

    @Autowired
    private StudentRepository repository;

    @Test
    public void testSaveStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Bharath");
        student.setMarks(100);
        repository.save(student);

        Student savedStudent = repository.findById(1L).orElseThrow();
        assertEquals("Bharath", savedStudent.getName());
        assertEquals(100, savedStudent.getMarks());
    }

}
