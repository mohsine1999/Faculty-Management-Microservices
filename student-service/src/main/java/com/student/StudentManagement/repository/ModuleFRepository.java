package com.student.StudentManagement.repository;

import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.model.Filiere;
import com.student.StudentManagement.model.ModuleF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleFRepository extends JpaRepository<ModuleF,Long> {
    List<ModuleF> getModuleFByFiliere(Filiere filiere);

}
