package com.example.notes.utils.mapper;

import com.example.notes.entity.Note;
import com.example.notes.entity.NoteDto;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NoteMapper {

    public NoteDto mapNoteToNoteDto(Note note) {

        return NoteDto.builder()
                .name(note.getName())
                .content(note.getContent())
                .createdAt(longToString(note.getCreatedAt()))
                .build();
    }

    public List<NoteDto> mapListNoteToListNoteDto(List<Note> list) {

        List<NoteDto> noteDtoList = new ArrayList<>();
        for (Note n : list) {
            noteDtoList.add(mapNoteToNoteDto(n));
        }
        return noteDtoList;
    }

    public String longToString (Long longDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = ZoneId.of("UTC");
        return  Instant.ofEpochMilli(longDate).atZone(zoneId).format(dateTimeFormatter);
    }

    public Long stringToLong (String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse(dateString);
        return date.getTime();
    }
}
