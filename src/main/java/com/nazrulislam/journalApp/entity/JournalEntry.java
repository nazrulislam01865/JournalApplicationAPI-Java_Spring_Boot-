package com.nazrulislam.journalApp.entity;

import java.rmi.server.ObjID;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "journalEntries")
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime createdAt;


}
