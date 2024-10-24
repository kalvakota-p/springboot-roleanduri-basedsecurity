package dev.phani.roleanduribasedsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noAuth")
public class NoAuthController {

    @GetMapping("/forbidden")
    public ResponseEntity<String> noAuthRequest() {
        return new ResponseEntity<>("URI Not Authorized", HttpStatus.FORBIDDEN);
    }
}
