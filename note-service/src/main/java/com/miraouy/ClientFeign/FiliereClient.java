package com.miraouy.ClientFeign;

import com.miraouy.dto.Response.Filiere;
import com.miraouy.dto.Response.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(value = "service-etudiant")

//@FeignClient(name = "service-filiere", path = "/api/filieres", url = "http://localhost:8081")
public interface FiliereClient {
    @GetMapping("/api/filieres/{id}")
    public Filiere viewFiliere(@PathVariable Long id);
}
