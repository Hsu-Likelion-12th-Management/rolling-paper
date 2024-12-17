package com.likelion.rolling_paper.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("test")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok("server on!");
    }
}
