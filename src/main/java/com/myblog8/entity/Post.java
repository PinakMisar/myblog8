package com.myblog8.entity;

import lombok.Data; //to remove boilerplate(extra line of code) code in your package

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(

        name = "posts" ,
        uniqueConstraints =
        {
            @UniqueConstraint(columnNames={"title"})}

)
@Data //it's a Lambok annotation
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title" , nullable = false)
    private String title;
    @Column(name = "discription" , nullable = false)
    private String discription;
    @Column(name = "content" , nullable = false)
    private String content;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Comment> comment = new ArrayList<Comment>();


}
