package com.radouaneoubakhane.serviceinscription.openfeingClients;


import com.radouaneoubakhane.serviceinscription.dto.RespenseFiliereDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "filier-service", url = "http://localhost:8083")
public interface FiliereClient {
    @GetMapping(value = "/api/filieres/{filierId}", produces = "application/json")
    RespenseFiliereDto getFilierById(@PathVariable long filierId);

    @GetMapping(value = "/api/filieres", produces = "application/json")
    List<RespenseFiliereDto> viewFilieres();
}




