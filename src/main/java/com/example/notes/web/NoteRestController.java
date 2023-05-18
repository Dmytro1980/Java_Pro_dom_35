package com.example.notes.web;

import com.example.notes.entity.Note;
import com.example.notes.entity.NoteDto;
import com.example.notes.service.NoteService;
import com.example.notes.utils.mapper.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteRestController {

    private final NoteService noteService;

    private final NoteMapper noteMapper;

    //note/all - find all notes
    @GetMapping("/all")
    public List<NoteDto> getById() {
        return
                this.noteMapper.mapListNoteToListNoteDto(noteService.findALL());

    }

    //note/id?id={id} - find note by id
    @GetMapping("/id")
    public NoteDto getById(@RequestParam("id") Integer id) {
        return this.noteMapper
                .mapNoteToNoteDto(
                        noteService.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("no note with id = " + id)));
    }

    //    "/note/add?name={name}&content={content}    - add new note
    // добавление в Notes новой заметки
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Note add(
            @RequestParam("name") String name,
            @RequestParam("content") String content) {

        Long dateAddNote = ZonedDateTime.now().toInstant().toEpochMilli();

        return noteService.save(new Note(name, content, dateAddNote));
    }

    //  /note/delete?note_id={note_id} - delete note by id
    @GetMapping("/delete")
    public void deleteById(@RequestParam("id") Integer id) {
        noteService.deleteById(id);
    }

    //  /note/update?id={id}?content={content} - update note by id
    @GetMapping("/update")
    public void getById(@RequestParam("id") Integer id,
                        @RequestParam("content") String content) {
        noteService.update(id, content);
    }

    //  /note/find_by_name?text={text} - find by name or content
    @GetMapping("/find_by_name")
    public List<NoteDto> findByName(@RequestParam("text") String text) {
        return this.noteMapper.mapListNoteToListNoteDto(noteService.findByName(text));
    }

    //  /note/find_by_date?dateBegin={dateBegin}?dateEnd={dateEnd} - find by date
    // format of date - dd-MM-yyyy
    @GetMapping("/find_by_date")
    public List<NoteDto> findByDate(
            @RequestParam("dateBegin") String dateBegin,
            @RequestParam("dateEnd") String dateEnd) throws ParseException {
        return this.noteMapper.mapListNoteToListNoteDto(noteService.findByDate(dateBegin, dateEnd));
    }
}
