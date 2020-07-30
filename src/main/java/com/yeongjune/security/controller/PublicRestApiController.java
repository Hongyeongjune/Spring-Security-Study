package com.yeongjune.security.controller;

import com.yeongjune.security.domain.entity.User;
import com.yeongjune.security.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class PublicRestApiController {

    private final UserRepository userRepository;

    @GetMapping("test1")
    public String test1() {
        return "API TEST 1";
    }

    @GetMapping("test2")
    public String test2() {
        return "API TEST 2";
    }

    @GetMapping("users")
    public List<User> allUsers() {
        return this.userRepository.findAll();
    }
}
