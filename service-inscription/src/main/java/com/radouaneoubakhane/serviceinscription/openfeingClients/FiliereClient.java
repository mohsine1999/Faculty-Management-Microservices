package com.radouaneoubakhane.serviceinscription.openfeingClients;


import com.radouaneoubakhane.serviceinscription.dto.FilierResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "filier-service", url = "http://localhost:8081", path = "/api/filieres")
public interface FiliereClient {
    @GetMapping(produces = "application/json")
    FilierResponse getFilierById(long filierId);
}




