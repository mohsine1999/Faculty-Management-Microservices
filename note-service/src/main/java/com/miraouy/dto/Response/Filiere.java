package com.miraouy.dto.Response;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Data
public class Filiere {
    private Long id;
    private String name;
}
