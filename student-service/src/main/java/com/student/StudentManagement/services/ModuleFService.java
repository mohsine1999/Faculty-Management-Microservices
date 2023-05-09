package com.student.StudentManagement.services;

import com.student.StudentManagement.dto.RequestModuleFDto;
import com.student.StudentManagement.dto.RespenseModuleFDto;
import com.student.StudentManagement.model.ModuleF;
import com.student.StudentManagement.model.ModulePojo;

import java.util.List;

public interface ModuleFService {
    RequestModuleFDto createModuleF(RequestModuleFDto requestModuleFDto);

    public void saveModule(ModulePojo dataPojo);

    List<RespenseModuleFDto> getAllModuleFs();

    RespenseModuleFDto getModuleFById(Long id );

    RequestModuleFDto updateModuleF(Long id,RequestModuleFDto moduleF);

    void deleteModuleF(Long id);
}
