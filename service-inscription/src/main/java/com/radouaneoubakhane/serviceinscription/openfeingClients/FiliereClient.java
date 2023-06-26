package com.radouaneoubakhane.serviceinscription.openfeingClients;


import com.radouaneoubakhane.serviceinscription.dto.RespenseFiliereDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "SERVICE-ETUDIANT")
public interface FiliereClient {
    @GetMapping( "/api/filieres/{filierId}")
    RespenseFiliereDto getFilierById(@PathVariable long filierId);

    @GetMapping("/api/filieres")
    List<RespenseFiliereDto> viewFilieres();
}




