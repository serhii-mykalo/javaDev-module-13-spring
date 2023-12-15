package com.example.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    private UUID id;

    @Size(min = 2, max = 250)
    private String title;

    @NotBlank
    private String content;
}
