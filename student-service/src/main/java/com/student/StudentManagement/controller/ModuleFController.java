package com.student.StudentManagement.controller;

import com.student.StudentManagement.dto.RequestModuleFDto;
import com.student.StudentManagement.dto.RespenseModuleFDto;
import com.student.StudentManagement.model.Filiere;
import com.student.StudentManagement.model.ModuleF;
import com.student.StudentManagement.model.ModulePojo;
import com.student.StudentManagement.services.ModuleFService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moduleFs")
@RequiredArgsConstructor
public class ModuleFController {
    private final ModuleFService moduleFService;


    @PostMapping
    public void saveModule(@RequestBody ModulePojo dataPojo) {
        moduleFService.saveModule(dataPojo);
    }

    @GetMapping
    public List<RespenseModuleFDto> viewModules() {
        return moduleFService.getAllModuleFs();

    }

    @GetMapping("/{id}")
    public RespenseModuleFDto viewModule(@PathVariable(value = "id") Long id) {
        return moduleFService.getModuleFById(id);

    }


    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable(value = "id") Long id) {
        moduleFService.deleteModuleF(id);
    }


    @PutMapping("/{id}")
    public RequestModuleFDto updateModuleF(@PathVariable Long id , @RequestBody RequestModuleFDto requestModuleFDto){
        return moduleFService.updateModuleF(id,requestModuleFDto);
    }
}
