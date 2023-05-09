package com.student.StudentManagement.services;

import com.student.StudentManagement.dto.RequestFiliereDto;
import com.student.StudentManagement.dto.RespenseFiliereDto;
import com.student.StudentManagement.model.Filiere;

import java.util.List;
import java.util.Optional;

public interface FiliereService {
    RequestFiliereDto createFiliere(RequestFiliereDto requestFiliereDto);

    List<RespenseFiliereDto> getAllFilieres();

    RespenseFiliereDto getFiliereById(Long id);


    RequestFiliereDto updateFiliere(Long id, RequestFiliereDto filiere);

    void deleteFiliere(Long id);
}

