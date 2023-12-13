package com.example.data.entity;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private UUID id;
    private String title;
    private String content;
}
