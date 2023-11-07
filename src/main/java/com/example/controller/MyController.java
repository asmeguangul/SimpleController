package com.example.controller;//package com.example.controller;
//
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1")
//public class MyController {
//
//    @GetMapping("/message")
//    public ResponseEntity<String> getString(){
//
//        String message = "my first api";
//
//        if(message== null  || message=="" )
//            throw new IllegalArgumentException("string can't be null");
//        return ResponseEntity.ok(message);
//
//    }
//
//
//    @PostMapping("/reverse")
//    public ResponseEntity<String> reverseString(@RequestParam String input) {
//        if (input == null || input.isEmpty()) {
//          throw new IllegalArgumentException("invalid input");
//        }
//        StringBuilder reversed = new StringBuilder(input).reverse();
//        return ResponseEntity.ok(reversed.toString());
//    }
//
//
//
//    @PostMapping("/factors")
//    public ResponseEntity<List<Integer>> getFactors(@RequestBody int input) {
//
//        List<Integer> factorsOfInput= factors(input); // factors of input
//        return ResponseEntity.ok(factorsOfInput);
//
//    }
//
//
//        public List<Integer> factors(int number){
//        List<Integer> factors = new ArrayList<>();
//        for (int i=1; i<=number/2; i++){
//            if(number%i==0){
//                factors.add(i);
//            }
//        }
//        return factors;
//        }
//
//
//
//
//
//
//
//
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class MyController {

    @Autowired
    private  RestTemplate restTemplate;

    private String apiURL = "https://jsonplaceholder.typicode.com/users";

    @GetMapping("/user1")
    public List<User> getUsers() {
        ResponseEntity<User[]> response =
                restTemplate.exchange(apiURL, HttpMethod.GET, null, User[].class);

        List<User> userList = Arrays.stream(response.getBody())
                .map(user -> new User(user.getName(), user.getEmail(), user.getZipcode()))
                .collect(Collectors.toList());
        return userList;
    }
}