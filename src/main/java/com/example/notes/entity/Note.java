package com.example.notes.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
@Table(name = "Notes")
public class Note {

    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("content")
    private String content;

    @Column("created_at")
    private Long createdAt;

    public Note(String name, String content, Long dateAddNote) {
        this.name = name;
        this.content = content;
        this.createdAt = dateAddNote;
    }
}
