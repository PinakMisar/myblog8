package com.myblog8.service.Impl;

import com.myblog8.entity.Comment;
import com.myblog8.entity.Post;
import com.myblog8.exception.ResourceNotFound;
import com.myblog8.payload.CommentDto;
import com.myblog8.repository.CommentRepository;
import com.myblog8.repository.PostRepository;
import com.myblog8.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService
{

    private  ModelMapper modelMapper;

    private CommentRepository commentRepo;

    private PostRepository postRepo;
    public CommentServiceImpl(CommentRepository commentRepo,
                              PostRepository postRepo,
                              ModelMapper modelMapper)
    {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto,long postId)
    {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post not found with id:" + postId)
        );
//        Comment comment = new Comment();
//       comment.setId(commentDto.getId());
//       comment.setName(commentDto.getName());
//       comment.setEmail(commentDto.getEmail());
//       comment.setBody(commentDto.getBody());

        Comment comment = mapToComment(commentDto);
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());

        return dto;
    }

    @Override
    public void deleteCommentById(long id)
    {
      commentRepo.deleteById(id);
    }

    @Override
    public CommentDto updateCommentById(CommentDto commentDto, long id)
    {
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Comment is not found")
        );

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(updatedComment.getId());
        dto.setName(updatedComment.getName());
        dto.setEmail(updatedComment.getEmail());
        dto.setBody(updatedComment.getBody());

        return dto;
    }
    public Comment mapToComment(CommentDto commentDto)
    {
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

}
