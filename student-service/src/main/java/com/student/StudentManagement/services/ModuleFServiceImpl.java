package com.student.StudentManagement.services;

import com.student.StudentManagement.dto.RequestFiliereDto;
import com.student.StudentManagement.dto.RequestModuleFDto;
import com.student.StudentManagement.dto.RespenseModuleFDto;
import com.student.StudentManagement.exceptions.StudentServiceRequestException;
import com.student.StudentManagement.model.Filiere;
import com.student.StudentManagement.model.ModuleF;
import com.student.StudentManagement.model.ModulePojo;
import com.student.StudentManagement.repository.FilierRepository;
import com.student.StudentManagement.repository.ModuleFRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ModuleFServiceImpl implements ModuleFService {

    private final ModuleFRepository moduleFRepository;
    private final FilierRepository filierRepository;

    @Override
    public RequestModuleFDto createModuleF(RequestModuleFDto requestModuleFDto) {

        RequestModuleFDto dto = RequestModuleFDto.builder().build();
        ModuleF moduleF1 = new ModuleF();
        BeanUtils.copyProperties(requestModuleFDto, moduleF1);
        Filiere filiere = new Filiere();

        ModuleF storedModuleF = moduleFRepository.save(moduleF1);
        BeanUtils.copyProperties(storedModuleF, dto);
        return dto;
    }

    @Override
    public void saveModule(ModulePojo dataPojo) {
        Filiere filiere = filierRepository.findById(dataPojo.getIdFiliere())
                .orElseThrow(() -> new StudentServiceRequestException("Filiere Not Found"));
        ModuleF moduleF = new ModuleF();
        BeanUtils.copyProperties(dataPojo, moduleF);
        moduleF.setFiliere(filiere);
        filierRepository.save(filiere);
        moduleFRepository.save(moduleF);
    }

    @Override
    public List<RespenseModuleFDto> getAllModuleFs() {
        List<ModuleF> modules = moduleFRepository.findAll();
        List<RespenseModuleFDto> dtos = new ArrayList<>();
        for (ModuleF i : modules) {
            RespenseModuleFDto response = RespenseModuleFDto.builder()
                    .name(i.getName())
                    .build();
            dtos.add(response);
        }
        return dtos;
    }

    @Override
    public RespenseModuleFDto getModuleFById(Long id) {
        RespenseModuleFDto dto = RespenseModuleFDto.builder().build();
        // ModuleF moduleF = moduleFRepository.findById(id).get();
        Optional<ModuleF> opt = moduleFRepository.findById(id);
        ModuleF moduleF;
        if (opt.isPresent()) {
            moduleF = opt.get();
        } else {
            throw new StudentServiceRequestException("Module not found for id :: " + id);
        }
        BeanUtils.copyProperties(moduleF, dto);
        return dto;
    }

    @Override
    public RequestModuleFDto updateModuleF(Long id, RequestModuleFDto requestModuleFDto) {

        ModuleF moduleF = moduleFRepository.findById(id).orElseThrow(() -> new StudentServiceRequestException("module not found !"));
        RequestModuleFDto dto = RequestModuleFDto.builder().build();
        BeanUtils.copyProperties(moduleF, dto);
        if (requestModuleFDto.getName() != null) dto.setName(requestModuleFDto.getName());
        if (requestModuleFDto.getFiliere() != null) dto.setFiliere(requestModuleFDto.getFiliere());

        BeanUtils.copyProperties(dto, moduleF);
        ModuleF newModule = moduleFRepository.save(moduleF);
        BeanUtils.copyProperties(newModule, dto);
        return dto;
        //ok


    }

    @Override
    public void deleteModuleF(Long id) {
        moduleFRepository.deleteById(id);
    }

    @Override
    public List<RespenseModuleFDto> getModuleByFiliere(Long id) {
        Filiere filiere = filierRepository.findById(id)
                .orElseThrow(() -> new StudentServiceRequestException("Filiere Not Found"));

        List<ModuleF> modules = moduleFRepository.getModuleFByFiliere(filiere);
        List<RespenseModuleFDto> dtos = new ArrayList<>();
        for (ModuleF i : modules) {
            RespenseModuleFDto response = RespenseModuleFDto.builder()
                    .id(i.getId())
                    .name(i.getName())
                    .build();
            dtos.add(response);
        }
        return dtos;
    }

}
