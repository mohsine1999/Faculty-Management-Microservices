package com.miraouy.dto.Response;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteResponseDto {

    private Float note;
    private Student student;
    private ModuleF module;
}
