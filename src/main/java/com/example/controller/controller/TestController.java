package com.example.controller.controller;

import com.example.service.dto.NoteDto;
import com.example.service.exception.NoteNotFoundException;
import com.example.service.mapper.NoteMapper;
import com.example.service.service.NoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
@Validated
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private NoteService noteService;
    @Autowired private NoteMapper noteMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView testPage() throws NoteNotFoundException {
        ModelAndView result =  new ModelAndView("notes/test");
        result.addObject("notes", noteMapper.toNoteResponses(noteService.findAll()));
        return result;
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public ModelAndView createNote(
            @RequestParam(value="title") @Size(min = 1, max = 250) String title,
            @RequestParam(value="content") @NotBlank String content) throws NoteNotFoundException {
        NoteDto dto = new NoteDto();
        dto.setTitle(title);
        dto.setContent(content);
        noteService.save(dto);
        return testPage();
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public ModelAndView noteList() throws NoteNotFoundException {
        ModelAndView result = new ModelAndView("notes/listNotes");
        result.addObject("notes", noteMapper.toNoteResponses(noteService.findAll()));
        return result;
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public ModelAndView getNoteForEdit(@NotEmpty @RequestParam(value="id") String id) throws NoteNotFoundException {
        UUID uuid = UUID.fromString(id);
        ModelAndView result = new ModelAndView("notes/updateNote");
        result.addObject("note", noteMapper.toNoteResponse(noteService.findById(uuid)));
        return result;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public ModelAndView updateNote(
            @NotEmpty @RequestParam(value="id") String id,
            @Size(min = 1, max = 250) @RequestParam(value="title") String title,
            @NotEmpty @RequestParam(value="content") String content) throws NoteNotFoundException {
        NoteDto dto = new NoteDto();
        dto.setId(UUID.fromString(id));
        dto.setTitle(title);
        dto.setContent(content);
        noteService.update(dto);
        return noteList();
    }

    @DeleteMapping("/delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public ModelAndView deleteNoteById(@Valid @NotNull @RequestParam(value="id") String id) throws NoteNotFoundException {
        noteService.deleteById(UUID.fromString(id));
        return noteList();
    }
}
