package com.example.notedown;

import com.example.notedown.model.User;
import com.example.notedown.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoteDownApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteDownApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository) {
//        if (userRepository.count() == 0) {
//            return args -> {
//                userRepository.save(new User("John", "pass123"));
//                userRepository.save(new User("Jane", "pass123"));
//            };
//        }
//        return args -> {};
//    }
}
