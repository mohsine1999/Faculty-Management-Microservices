package com.miraouy.ClientFeign;

import com.miraouy.dto.Response.ModuleF;
import com.miraouy.dto.Response.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-module", path = "/api/modules", url = "http://localhost:8200")
public interface ModuleClient {
    @GetMapping("/{id}")
    ModuleF viewModule(@PathVariable Long id);


}

