package com.myblog8.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto
{
    private Long id;
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    private String body;
}
