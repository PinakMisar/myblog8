package com.myblog8.controller;

import com.myblog8.payload.CommentDto;
import com.myblog8.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //http://localhost:8080/api/comments/{post_Id}
    @PostMapping("{postId}")
    public ResponseEntity<?> saveComment(@Valid @RequestBody CommentDto commentDto,
                                         BindingResult result,
                                         @PathVariable Long postId) {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CommentDto dto = commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable long id)
    {
        commentService.deleteCommentById(id);
        return  new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateCommentById(@RequestBody CommentDto commentDto,@PathVariable long id)
    {

        CommentDto dto = commentService.updateCommentById(commentDto, id);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
}
