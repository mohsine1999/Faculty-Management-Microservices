package com.student.StudentManagement.services;

import com.student.StudentManagement.exceptions.StudentServiceRequestException;
import com.student.StudentManagement.model.Carriere;
import com.student.StudentManagement.model.CarrierePojo;
import com.student.StudentManagement.model.Student;
import com.student.StudentManagement.repository.CarriereRepository;
import com.student.StudentManagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarriereServiceImpl implements CarriereService {
    private final CarriereRepository carriereRepository;
    private final StudentRepository studentRepository;

    @Override
    public void saveCarriere(CarrierePojo carrierePojo) {
        Student student = studentRepository.findById(carrierePojo.getStudentId())
                .orElseThrow(() -> new StudentServiceRequestException(" Student not found"));
        Carriere carriere = new Carriere();
        BeanUtils.copyProperties(carrierePojo, carriere);
        carriere.setStudent(student);
        studentRepository.save(student);
        carriereRepository.save(carriere);
    }


    @Override
    public void updateCarriere(Carriere carriere) {

    }
}
