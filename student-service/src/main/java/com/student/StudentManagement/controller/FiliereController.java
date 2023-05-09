package com.student.StudentManagement.controller;

import com.student.StudentManagement.dto.RequestFiliereDto;
import com.student.StudentManagement.dto.RespenseFiliereDto;
import com.student.StudentManagement.model.Filiere;
import com.student.StudentManagement.services.FiliereService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filieres")
@RequiredArgsConstructor
public class FiliereController {
    private final FiliereService filiereService;

    @PostMapping
    public RespenseFiliereDto createFiliere(@RequestBody Filiere filiere) {
        RespenseFiliereDto adResp = RespenseFiliereDto.builder().build();
        RequestFiliereDto dto = RequestFiliereDto.builder().build();
        BeanUtils.copyProperties(filiere, dto);
        RequestFiliereDto dto1 = filiereService.createFiliere(dto);
        BeanUtils.copyProperties(dto1, adResp);


        return adResp;
    }

    @GetMapping
    public List<RespenseFiliereDto> viewFilieres() {
        return filiereService.getAllFilieres();

    }

    @GetMapping("/{id}")
    public RespenseFiliereDto viewFiliere(@PathVariable(value = "id") Long id) {
        return filiereService.getFiliereById(id);

    }


    @DeleteMapping("/{id}")
    public void deleteFiliere(@PathVariable(value = "id") Long id) {
        filiereService.deleteFiliere(id);
    }


    @PutMapping("/{id}")
    public RequestFiliereDto updateFiliere(@PathVariable Long id, @RequestBody RequestFiliereDto filiere) {
        return filiereService.updateFiliere(id,filiere);

    }
}
