package com.web.emp.controls;

import com.web.emp.records.Posts;
import com.web.emp.services.JsonPlaceHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonPlaceHolderController {

    @Autowired
    private JsonPlaceHolderService jsonPlaceHolderService;

    @GetMapping
    public List<Posts> getAllPosts() {
        List<Posts> posts = jsonPlaceHolderService.getAllPosts();
        return posts;
    }
}
