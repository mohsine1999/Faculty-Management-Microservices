package com.student.StudentManagement.controller;

import com.student.StudentManagement.model.Carriere;
import com.student.StudentManagement.model.CarrierePojo;
import com.student.StudentManagement.services.CarriereService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carrieres")
@RequiredArgsConstructor
public class CarriereController {
    private final CarriereService carriereService;
    @PostMapping
    public void saveCarriere(@RequestBody CarrierePojo carrierePojo){
        carriereService.saveCarriere(carrierePojo);
    }

}
