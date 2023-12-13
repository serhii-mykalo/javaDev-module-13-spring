package com.example.data.repository;

import com.example.data.entity.Note;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class NoteRepository {

    private List<Note> notes = new ArrayList<>();

    public Note save(Note note) {
        if (Objects.isNull(note.getId())) {
            note.setId(UUID.randomUUID());
            this.notes.add(note);
        } else {
            Optional<Note> optionalNote = this.notes.stream()
                    .filter(n -> n.getId().equals(note.getId()))
                    .findFirst();
            if (optionalNote.isPresent()) {
                this.notes.remove(optionalNote.get());
                this.notes.add(note);
            }
        }
        return note;
    }

    public void update(Note note) {
        notes.set(notes.indexOf(note), note);
        log.info("Note updated");
    }

    public Optional<Note> findById(UUID id) {
        return this.notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();
    }

    public List<Note> findAll() {
        return this.notes;
    }

    public void deleteById(UUID id) {
        this.notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .ifPresent(this.notes::remove);
    }

    @PostConstruct
    public void init() {
        Note noteOne = new Note();
        noteOne.setTitle("Title note 1");
        noteOne.setContent("Content note 1");
        Note noteTwo = new Note();
        noteTwo.setTitle("Title note 2");
        noteTwo.setContent("Content note 2");
        Note noteThree = new Note();
        noteThree.setTitle("Title note 3");
        noteThree.setContent("Content note 3");
        save(noteOne);
        save(noteTwo);
        save(noteThree);
    }
}
