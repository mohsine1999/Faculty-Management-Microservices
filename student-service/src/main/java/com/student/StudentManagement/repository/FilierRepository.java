package com.student.StudentManagement.repository;

import com.student.StudentManagement.dto.RespenseFiliereDto;
import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilierRepository extends JpaRepository<Filiere,Long> {
    List<Filiere> getFiliereByDiplomat(Diplomat diplomat);
}
