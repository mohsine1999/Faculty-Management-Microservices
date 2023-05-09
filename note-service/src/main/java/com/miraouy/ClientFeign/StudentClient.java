package com.miraouy.ClientFeign;

import com.miraouy.dto.Response.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-etudiant", path = "/api/students", url = "localhost:8081")
public interface StudentClient {
    @GetMapping("/getStudent/{apogee}")
    public Student viewStudent(@PathVariable Long apogee);
}
