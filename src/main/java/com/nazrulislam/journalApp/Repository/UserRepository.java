package com.nazrulislam.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nazrulislam.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    public User findByUserName(String userName);


}
