package dev.phani.roleanduribasedsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AppController {

    @GetMapping("/home")
    public ResponseEntity<String> welcomeHome() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
