package com.yeongjune.security.service;

import com.yeongjune.security.domain.entity.User;
import com.yeongjune.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DataBaseInit implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Delete all
        this.userRepository.deleteAll();

        // create users
        User yeongjune = new User("yeongjune", passwordEncoder.encode("yeongjune"),"USER","");
        User admin = new User("admin", passwordEncoder.encode("admin"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager", passwordEncoder.encode("manager"),"MANAGER","ACCESS_TEST1");

        List<User> users = Arrays.asList(yeongjune, admin, manager);

        // save to db
        this.userRepository.saveAll(users);
    }
}
