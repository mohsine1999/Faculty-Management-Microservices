package com.miraouy.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RefreshScope
public class ConfigTestController {
   /* @Value("${note.params.x}")
    private String x;
    @Value("${note.params.y}")
    private String y;
    @Value("${hind.out.hello}")
    private String hind;
    @GetMapping("/params")*/
    //public Map<String,String> TesteParams(){
    //  return Map.of("x",x,"y",y,"name of oumzguir",hind);
    //  }
}
