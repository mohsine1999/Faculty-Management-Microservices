package com.miraouy.ClientFeign;

import com.miraouy.dto.Response.ModuleF;
import com.miraouy.dto.Response.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(value = "service-etudiant")
//@FeignClient(name = "service-module", path = "/api/moduleFs", url = "http://localhost:8081")
public interface ModuleClient {
    @GetMapping("/api/moduleFs/{id}")
    ModuleF viewModule(@PathVariable Long id);


}

