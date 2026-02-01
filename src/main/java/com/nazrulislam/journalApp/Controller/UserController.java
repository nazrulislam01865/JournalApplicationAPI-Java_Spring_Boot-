package com.nazrulislam.journalApp.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nazrulislam.journalApp.Service.UserService;
import com.nazrulislam.journalApp.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {
     @Autowired
    private UserService UserService;

    @PostMapping("/createEntry")
    public ResponseEntity<User> create(@RequestBody User entry) {
        try{
            UserService.create(entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(entry,HttpStatus.BAD_REQUEST);
        }
        
    } 

    @GetMapping("/showAll")
    public List<User> getAllEntries() {
        return UserService.getAll();  
    }

    @GetMapping("id/{id}")
    public ResponseEntity<User> getEntryById(@PathVariable ObjectId id) {
        try{
            User User = UserService.getById(id).orElse(null);
            if(User==null){
                return new ResponseEntity<>(User,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(User,HttpStatus.OK);
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
            // or throw an exception, or return ResponseEntity
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id) {
        UserService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id, @RequestBody User updatedEntry) {
    //     try{
    //                 User User = UserService.getById(id).orElse(null);
    //     if (User != null) {
    //         User.setUserName(updatedEntry.getUserName() != null && !updatedEntry.getUserName().equals("")? updatedEntry.getUserName():User.getUserName());
    //         User.setPassword(updatedEntry.getPassword() != null && !updatedEntry.getPassword().equals("")? updatedEntry.getPassword():User.getPassword());
    //     }
    //     else{
    //         return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    //     }
    //     UserService.create(User);
    //     return new ResponseEntity<>(User,HttpStatus.OK);

    //     }catch(Exception e){
    //         System.out.println(e.getMessage());
    //         return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    //     }

    // }

    @PutMapping("/{userName}")
    public ResponseEntity<?> Update(@PathVariable String userName, @RequestBody User updatedEntry) {
        try{
            User User = UserService.findByUserName(userName);
            if (User != null) {
                User.setUserName(updatedEntry.getUserName());
                User.setPassword(updatedEntry.getPassword());  
            }
            else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

            }
            UserService.create(User);
            return new ResponseEntity<>(User,HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

}
