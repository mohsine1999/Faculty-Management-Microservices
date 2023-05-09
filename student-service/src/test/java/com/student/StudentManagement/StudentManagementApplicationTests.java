package com.student.StudentManagement;

import com.student.StudentManagement.dto.RequestStudentDto;
import com.student.StudentManagement.enumurations.Gender;
import com.student.StudentManagement.model.Student;
import com.student.StudentManagement.model.StudentPojo;
import com.student.StudentManagement.repository.StudentRepository;
import com.student.StudentManagement.services.StudentService;
import com.student.StudentManagement.services.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentService studentService ;

    @InjectMocks
    private StudentServiceImpl employeeServiceImpl;

    @InjectMocks
    private Student std;

    @InjectMocks
    private  Student student1;
    @InjectMocks

    RequestStudentDto studentByApogee;

    @BeforeEach
    public void setup(){



        std = Student.builder()
                    .cin("jhgf")
                    .apogee(23456L)
                    .nom("kjh")
                    .prenom("hdhdhd")
                    .cne("jhdhdhd")
                    .email("shddsf@gmail.com")
                    .phone("234567")
                    .dateNaissance( new Date())
                    .lieuNaissance("sdfghj")
                    .adresse("gfds")
                    .genre(Gender.FEMALE)

                .build();
         studentByApogee = studentService.getStudentByApogee(23456L);

         student1 = Student.builder()
                    .cin(studentByApogee.getCin())
                    .apogee(studentByApogee.getApogee())
                    .nom(studentByApogee.getNom())
                    .prenom(studentByApogee.getPrenom())
                    .cne(studentByApogee.getCne())
                    .email(studentByApogee.getEmail())
                    .phone(studentByApogee.getPhone())
                    .dateNaissance(studentByApogee.getDateNaissance())
                    .lieuNaissance(studentByApogee.getLieuNaissance())
                    .adresse(studentByApogee.getAdresse())
                    .genre(studentByApogee.getGenre())
                    .build();




    }

    @DisplayName("JUnit test for getStudent method")
    @Test
    public void getStudentByApogee() {
        std = Student.builder()
                .cin("jhgf")
                .apogee(23456L)
                .nom("kjh")
                .prenom("hdhdhd")
                .cne("jhdhdhd")
                .email("shddsf@gmail.com")
                .phone("234567")
                .dateNaissance( new Date())
                .lieuNaissance("sdfghj")
                .adresse("gfds")
                .genre(Gender.FEMALE)

                .build();

        RequestStudentDto requestStudentDto= RequestStudentDto.builder().build();
        BeanUtils.copyProperties(std,requestStudentDto);
        // providing knowledge
        when(studentService.getStudentByApogee(std.getApogee())).thenReturn(requestStudentDto);



    }

}
