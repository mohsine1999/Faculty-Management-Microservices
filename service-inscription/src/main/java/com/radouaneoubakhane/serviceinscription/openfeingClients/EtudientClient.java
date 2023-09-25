package com.radouaneoubakhane.serviceinscription.openfeingClients;


import com.radouaneoubakhane.serviceinscription.dto.EtudientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "SERVICE-ETUDIANT")
//@FeignClient(name = "etudient-service", url = "http://localhost:8081", path = "/api/students")

public interface EtudientClient {
    @PostMapping( "/api/students")
    void SaveStudent(EtudientRequest etudientRequest);

    @PostMapping( "/api/students/saveStudents")
    void saveStudents(List<EtudientRequest> etudientRequestList);
}
