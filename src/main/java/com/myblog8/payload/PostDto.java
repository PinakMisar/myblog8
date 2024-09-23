package com.myblog8.payload;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


//@Setter  // some times @data does not Work so we can use this
//@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto
{
      private long id;
      @NotEmpty
      @Size(min = 2, message = "Post title should have at least 2 characters")
      private String title;
      @NotEmpty
      @Size(min = 10, message = "Post description should have at least 10 characters")
      private String discription;
      @NotEmpty
      private String content;


}
