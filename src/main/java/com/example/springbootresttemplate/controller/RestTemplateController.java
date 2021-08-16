package com.example.springbootresttemplate.controller;

import com.example.springbootresttemplate.model.User;
import com.example.springbootresttemplate.payload.request.UserCreateRequest;
import com.example.springbootresttemplate.payload.request.UserUpdateRequest;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ModelMapper modelMapper;

    public static final org.apache.log4j.Logger logger = Logger.getLogger(RestTemplateController.class);

    @GetMapping (value = "/users")
    public String getUserList(){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<String>(headers);
        return  restTemplate.exchange("http://localhost:8081/api/users", HttpMethod.GET,entity,String.class).getBody();
    }

    @PostMapping(value = "/users")
    public String createUser(@RequestBody UserCreateRequest userCreateRequest){
        User user=modelMapper.map(userCreateRequest,User.class);
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity=new HttpEntity<User>(user,headers);
        return  restTemplate.exchange("http://localhost:8081/api/users", HttpMethod.POST,entity,String.class).getBody();// Post atılacak Proje portu
    }
    @PutMapping(value = "/users/{id}")
    public String updateUser(@PathVariable("id")  int id, @RequestBody UserUpdateRequest userUpdateRequest){
        User user=modelMapper.map(userUpdateRequest,User.class);
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity=new HttpEntity<User>(user,headers);
        return  restTemplate.exchange("http://localhost:8081/api/users/"+id, HttpMethod.PUT,entity,String.class).getBody(); //Put yapılacak Proje portu
    }
    @DeleteMapping(value = "/users/{id}")
    public  String deleteUser(@PathVariable("id") int id){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity=new HttpEntity<User>(headers);
        return  restTemplate.exchange("http://localhost:8081/api/users/"+id, HttpMethod.DELETE,entity,String.class).getBody();//Delete edilecek Proje portu
    }

}
