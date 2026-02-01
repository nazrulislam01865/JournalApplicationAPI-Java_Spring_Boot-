package com.nazrulislam.journalApp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nazrulislam.journalApp.Repository.JournalRepository;
import com.nazrulislam.journalApp.entity.JournalEntry;
import com.nazrulislam.journalApp.entity.User;

@Component
public class JournalEntryService {

    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UserService userService;

    public boolean create(JournalEntry entry, String userName){
        try{
            User user = userService.findByUserName(userName);
            entry.setCreatedAt(LocalDateTime.now());
            JournalEntry saved =  journalRepository.save(entry);
            user.getJournalEntries().add(saved);
            userService.create(user);
            return true;
        }catch(Exception e){
            
            return false;
        }

    }


    public boolean create(JournalEntry entry){
        try{
       
            journalRepository.save(entry);
            return true;
        }catch(Exception e){
            
            return false;
        }

    }

    public List<JournalEntry> getAll(){
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalRepository.findById(id);
    }

    public boolean deleteById(ObjectId id, String userName){
        try{
            User user = userService.findByUserName(userName);
            user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
            userService.create(user);
            journalRepository.deleteById(id);

            return true;

        }catch(Exception e){
            return false;
        }
        
        
    }
    
    public boolean updateById(ObjectId id){
        
        return true;
    }

}
