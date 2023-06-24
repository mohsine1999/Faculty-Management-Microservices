package com.student.StudentManagement.repository;

import com.student.StudentManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByApogee(Long apogee);

    @Query("SELECT s FROM student s WHERE s.email = ?1")
    Student getStudentByEmail(String email);



}
