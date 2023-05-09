package com.student.StudentManagement.repository;

import com.student.StudentManagement.model.Carriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarriereRepository extends JpaRepository<Carriere,Long> {

    //Carriere getCarriereByStudent();
}
