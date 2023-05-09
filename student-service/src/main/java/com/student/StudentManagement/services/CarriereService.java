package com.student.StudentManagement.services;

import com.student.StudentManagement.dto.RequestFiliereDto;
import com.student.StudentManagement.model.Carriere;
import com.student.StudentManagement.model.CarrierePojo;
import com.student.StudentManagement.model.Filiere;
import com.student.StudentManagement.model.Student;

import java.util.List;
import java.util.Optional;

public interface CarriereService {
    void saveCarriere (CarrierePojo carrierePojo);
    //Optional<Carriere> getCarriereByStudent(Student student);
    void updateCarriere(Carriere carriere);
}
