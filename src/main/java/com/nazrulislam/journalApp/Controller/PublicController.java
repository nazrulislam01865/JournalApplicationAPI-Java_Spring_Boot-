package com.nazrulislam.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nazrulislam.journalApp.Service.UserService;
import com.nazrulislam.journalApp.entity.User;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/Ok")
    public String checkHealth() {
        return "Application is running!";
    }

         @Autowired
    private UserService UserService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User entry) {
        try{
            UserService.create(entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(entry,HttpStatus.BAD_REQUEST);
        }
        
    } 

}
