package com.miraouy.ClientFeign;

import com.miraouy.dto.Response.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-etudiant")
public interface StudentClient {
    @GetMapping("/api/students/getStudent/{apogee}")
    public Student viewStudent(@PathVariable Long apogee);
}
