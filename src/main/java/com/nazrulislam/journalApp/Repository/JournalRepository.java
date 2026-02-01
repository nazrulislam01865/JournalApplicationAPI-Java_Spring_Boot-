package com.nazrulislam.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nazrulislam.journalApp.entity.JournalEntry;

public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId> {

    

}
