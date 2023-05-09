package com.miraouy.dto.Request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteRequestDto {

    private Float note;
    private Long apogee;
    private Long idModule;

}
