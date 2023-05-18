package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.repository.NoteRepository;
import com.example.notes.utils.mapper.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    private final NoteMapper noteMapper;

    public List<Note> findALL() {
        return this.noteRepository.findAll();
    }

    public Optional<Note> findById(Integer id) {
        return this.noteRepository.findById(id);
    }

    public Note save(Note note){
        return this.noteRepository.save(note);
    }

    public void deleteById(Integer id) {
        this.noteRepository.deleteById(id);
    }

    public void update(Integer id, String content) {

        if (this.noteRepository.existsById(id)) {
            this.noteRepository.updateById(id, content);
        }
    }

    public List<Note> findByName(String text) {
        return this.noteRepository.findByNameContainingOrContentContaining(text, text);
    }

    public List<Note> findByDate(String dateBegin, String dateEnd) throws ParseException {
        Long longDateBegin = noteMapper.stringToLong(dateBegin);
        Long longDateEnd = noteMapper.stringToLong(dateEnd);

        if (longDateBegin > longDateEnd) {
            return this.noteRepository.findByCreatedAtBetweenOrderByCreatedAt(longDateEnd, longDateBegin);
        }
            return this.noteRepository.findByCreatedAtBetweenOrderByCreatedAt(longDateBegin, longDateEnd);
    }
}
