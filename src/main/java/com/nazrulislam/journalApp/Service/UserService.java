package com.nazrulislam.journalApp.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nazrulislam.journalApp.Repository.UserRepository;
import com.nazrulislam.journalApp.entity.User;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean create(User entry){
        userRepository.save(entry);
        return true;
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }
    
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}
