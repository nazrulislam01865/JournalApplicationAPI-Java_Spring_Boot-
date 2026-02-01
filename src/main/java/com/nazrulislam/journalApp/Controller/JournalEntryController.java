package com.nazrulislam.journalApp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.nazrulislam.journalApp.Service.JournalEntryService;
import com.nazrulislam.journalApp.Service.UserService;
import com.nazrulislam.journalApp.entity.JournalEntry;
import com.nazrulislam.journalApp.entity.User;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> create(@PathVariable String userName, @RequestBody JournalEntry entry) {
        try{
            // User user = userService.findByUserName(userName);
            // if(user != null){
            //     journalEntryService.create(entry,user);
            // }
            journalEntryService.create(entry,userName);
            
            return new ResponseEntity<>(entry,HttpStatus.CREATED);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(entry,HttpStatus.BAD_REQUEST);
        }
        
    } 

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all !=null && user !=null){
            return new ResponseEntity<List<JournalEntry>>(all, HttpStatus.OK);   
        }
        return new ResponseEntity<String>("No Entries Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id) {
        try{
            JournalEntry journalEntry = journalEntryService.getById(id).orElse(null);
            if(journalEntry==null){
                return new ResponseEntity<>(journalEntry,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(journalEntry,HttpStatus.OK);
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
            // or throw an exception, or return ResponseEntity
    }

    @DeleteMapping("/delete/{userName}/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String userName, @PathVariable ObjectId id) {
        journalEntryService.deleteById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{userName}/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable String userName, @PathVariable ObjectId id, @RequestBody JournalEntry updatedEntry) {
        try{
            
            JournalEntry journalEntry = journalEntryService.getById(id).orElse(null);
            if (journalEntry != null) {
                journalEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("")? updatedEntry.getTitle():journalEntry.getTitle());
                journalEntry.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals("")? updatedEntry.getContent():journalEntry.getContent());
            }
            else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

            }
            journalEntryService.create(journalEntry);
            return new ResponseEntity<>(journalEntry,HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    // @PutMapping("/update/{id}")
    // public JournalEntry updateEntryById(
    //         @PathVariable String id,
    //         @RequestBody JournalEntry updatedEntry) {

    //     JournalEntry journalEntry = journalEntryService.getById(new ObjectId(id)).orElse(null);

    //     if (journalEntry == null) {
    //         return null;
    //     }

    //     if (updatedEntry.getTitle() != null && !updatedEntry.getTitle().isEmpty()) {
    //         journalEntry.setTitle(updatedEntry.getTitle());
    //     }

    //     if (updatedEntry.getContent() != null && !updatedEntry.getContent().isEmpty()) {
    //         journalEntry.setContent(updatedEntry.getContent());
    //     }

    //     journalEntryService.create(journalEntry); // or save()
    //     return journalEntry;
    // }

    

    // private Map<Long, JournalEntry> journalEntries = new HashMap<>();
    
    
    
    // @GetMapping("/journalEntries")
    // public List<JournalEntry> getAllEntries() {
    //     return new ArrayList<>(journalEntries.values());
    // }

    // @PostMapping("/createEntry")
    // public boolean create(@RequestBody JournalEntry entry) {
    //     long id = journalEntries.size() + 1;
    //     entry.setId((int) id);
    //     journalEntries.put(id, entry);
    //     return true;

    // }



}
