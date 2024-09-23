package com.myblog8.service;

import com.myblog8.payload.CommentDto;

public interface CommentService
{

 public CommentDto createComment(CommentDto commentDto,long postId);

  public void deleteCommentById(long id);

   public CommentDto updateCommentById(CommentDto commentDto, long id);
}
