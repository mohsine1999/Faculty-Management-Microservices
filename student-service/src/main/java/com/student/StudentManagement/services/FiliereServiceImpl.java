package com.student.StudentManagement.services;

import com.student.StudentManagement.dto.RequestFiliereDto;
import com.student.StudentManagement.dto.RespenseFiliereDto;
import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.exceptions.StudentServiceRequestException;
import com.student.StudentManagement.model.Filiere;
import com.student.StudentManagement.repository.FilierRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FiliereServiceImpl implements FiliereService {
    private final FilierRepository filierRepository;
    private RequestFiliereDto updatedf;

    @Override
    public RequestFiliereDto createFiliere(RequestFiliereDto requestFiliereDto) {
        RequestFiliereDto dto = RequestFiliereDto.builder().build();
        Filiere filiere1 = new Filiere();
        BeanUtils.copyProperties(requestFiliereDto, filiere1);
        Filiere storedFiliere = filierRepository.save(filiere1);
        BeanUtils.copyProperties(storedFiliere, dto);
        return dto;
    }

    @Override
    public List<RespenseFiliereDto> getAllFilieres() {
        List<Filiere> filieres = filierRepository.findAll();
        List<RespenseFiliereDto> respenseFiliereDtos = new ArrayList<>();
        for (Filiere i : filieres) {
            RespenseFiliereDto response = RespenseFiliereDto.builder()
                    .id(i.getId())
                    .name(i.getName())
                    .diplomat(i.getDiplomat())
                    .modules(i.getModules())
                    .build();
            respenseFiliereDtos.add(response);
        }
        return respenseFiliereDtos;
    }

    @Override
//    public List<Filiere> getFiliereByDiplomat(Diplomat diplomat) {
//      return filierRepository.getFiliereByDiplomat(diplomat);
//
//        }
    public List<RespenseFiliereDto> getFiliereByDiplomat(Diplomat diplomat) {
        List<Filiere> filieres = filierRepository.getFiliereByDiplomat(diplomat);
        RespenseFiliereDto respenseFiliereDto ;
        List<RespenseFiliereDto> respenseFiliereDtos = new ArrayList<>();
        for (Filiere f: filieres) {

            respenseFiliereDto = RespenseFiliereDto.builder()
                    .id(f.getId())
                    .name(f.getName())
                    .diplomat(f.getDiplomat())
                    .build();
            respenseFiliereDtos.add(respenseFiliereDto);


        }
        return  respenseFiliereDtos;

    }


    @Override
    public RespenseFiliereDto getFiliereById(Long id) {
        RespenseFiliereDto dto = RespenseFiliereDto.builder().build();

        Optional<Filiere> opt = filierRepository.findById(id);
        Filiere filiere;
        if (opt.isPresent()) {
            filiere = opt.get();
        } else {
            throw new StudentServiceRequestException("Filiere not found for id :: " + id);
        }
        BeanUtils.copyProperties(filiere, dto);
        return dto;
    }

    @Override
    public RequestFiliereDto updateFiliere(Long id,RequestFiliereDto requestFiliereDto) {
        Filiere filiere = filierRepository.findById(id).orElseThrow(()->new StudentServiceRequestException("filiere not found"));
         RequestFiliereDto dto = RequestFiliereDto.builder().build();
         BeanUtils.copyProperties(filiere,dto);
            if(requestFiliereDto.getName()!=null) dto.setName(requestFiliereDto.getName());
            BeanUtils.copyProperties(dto,filiere);
            Filiere newFiliere = filierRepository.save(filiere);
            BeanUtils.copyProperties(newFiliere,dto);
            return dto;
    }


    @Override
    public void deleteFiliere(Long id) {
        filierRepository.deleteById(id);
    }
}
