package com.nazrulislam.journalApp.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PutMapping
    public ResponseEntity<?> Update(@RequestBody User updatedEntry) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
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
