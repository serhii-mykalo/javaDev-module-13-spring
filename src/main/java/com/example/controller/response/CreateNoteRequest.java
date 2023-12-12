package com.example.controller.response;

import com.example.controller.request.NoteRequest;
import lombok.Data;

@Data
public class CreateNoteRequest extends NoteRequest {

    public CreateNoteRequest () {
    }

    public CreateNoteRequest (String title, String content) {
        super(title, content);
    }
}
