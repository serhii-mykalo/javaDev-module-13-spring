package com.example.controller.response;

import com.example.controller.request.NoteRequest;
import lombok.Data;

@Data
public class UpdateNoteRequest extends NoteRequest {

    public UpdateNoteRequest () {
    }

    public UpdateNoteRequest (String title, String content) {
        super(title, content);
    }
}
