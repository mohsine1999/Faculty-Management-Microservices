package com.student.StudentManagement.controller;

import com.student.StudentManagement.model.Carriere;
import com.student.StudentManagement.model.CarrierePojo;
import com.student.StudentManagement.services.CarriereService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrieres")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CarriereController {
    private final CarriereService carriereService;
    @PostMapping
    public void saveCarriere(@RequestBody CarrierePojo carrierePojo){
        carriereService.saveCarriere(carrierePojo);
    }

}
