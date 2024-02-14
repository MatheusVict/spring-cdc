package io.matheusvictor.springcommentservice;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final CommentRepository repository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return repository.save(comment);
    }

    @GetMapping
    public List<Comment> getComments() {
        return repository.findAll();
    }
}