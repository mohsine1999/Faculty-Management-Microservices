package com.student.StudentManagement.repository;

import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student getStudentByApogee(Long apogee);

    //khli hadi hna 7ta tsawlni 3liha : void deleteByApogee(Long apogee);


}
