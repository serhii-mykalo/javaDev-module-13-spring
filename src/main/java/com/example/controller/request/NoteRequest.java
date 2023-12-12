package com.example.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class NoteRequest {

    @Size(min = 3, max = 250)
    private String title;

    @NotBlank
    private String content;
}
