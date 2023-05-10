package com.student.StudentManagement.controller;

import com.student.StudentManagement.dto.RequestStudentDto;
import com.student.StudentManagement.dto.RespenseStudentDto;
import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.exceptions.StudentServiceRequestException;
import com.student.StudentManagement.model.Carriere;
import com.student.StudentManagement.model.StudentPojo;
import com.student.StudentManagement.repository.FilierRepository;
import com.student.StudentManagement.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final FilierRepository filierRepository;

    @PostMapping
    public void SaveStudent(@RequestBody StudentPojo data) {
        studentService.saveStudent(data);

    }

    @GetMapping("/getStudents")
    public List<RespenseStudentDto> viewStudents() {
        return studentService.getAllStudents();

    }

    @GetMapping("/getStudent/{apogee}")
    public RequestStudentDto viewStudent(@PathVariable(value = "apogee") String apogeeStr)  {
        if (apogeeStr == null || apogeeStr.trim().isEmpty()) {
            throw new StudentServiceRequestException("Apogee value is missing");
        }

        Long apogee = null;
        try {
            apogee = Long.parseLong(apogeeStr);
        } catch (NumberFormatException e) {
            throw new StudentServiceRequestException("Invalid apogee value: " + apogeeStr);
        }
        return studentService.getStudentByApogee(apogee);
    }

    @DeleteMapping("/{apogee}")
    public void deleteStudent(@PathVariable(value = "apogee") Long apogee) {
        studentService.deleteStudent(apogee);
    }

    @GetMapping("/{id}")
    public List<Carriere> getCarrieresByStudent(@PathVariable(name = "id") Long id) {
        return studentService.getCarrieresByStudentId(id);
    }

    @GetMapping("/getDiplomat/{apogee}")
    public Diplomat getCurrentDiplomat(@PathVariable(value = "apogee") Long apogee) {
        return studentService.getCurrentDiplomat(apogee);
    }

    @PutMapping("/{id}")
    public RequestStudentDto updateStudent(@PathVariable Long id, @RequestBody RequestStudentDto requestStudentDto) {
        return studentService.updateStudent(id, requestStudentDto);
    }

    @PostMapping("/saveStudents")
    public void saveStudents(@RequestBody List<StudentPojo> requestStudentDtos){
            for (StudentPojo std : requestStudentDtos) {
                StudentPojo studentPojo = StudentPojo.builder()
                        .cin(std.getCin())
                        .apogee(std.getApogee())
                        .nom(std.getNom())
                        .prenom(std.getPrenom())
                        .cne(std.getCne())
                        .email(std.getEmail())
                        .phone(std.getPhone())
                        .dateNaissance(std.getDateNaissance())
                        .lieuNaissance(std.getLieuNaissance())
                        .adresse(std.getAdresse())
                        .genre(std.getGenre())
                        .idFiliere(std.getIdFiliere())
                        .build();
                studentService.saveStudent(studentPojo);

            }
        }

}
