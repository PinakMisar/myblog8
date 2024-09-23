package com.myblog8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String body;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id",nullable = false)
    private Post post;

}
