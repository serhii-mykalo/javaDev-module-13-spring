package com.example.service.service;

import com.example.service.dto.NoteDto;
import com.example.service.exception.NoteNotFoundException;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    NoteDto save(NoteDto note);

    void update(NoteDto note) throws NoteNotFoundException;

    NoteDto findById(UUID id)  throws NoteNotFoundException;

    List<NoteDto> findAll()  throws NoteNotFoundException;

    void deleteById(UUID id)  throws NoteNotFoundException;
}
