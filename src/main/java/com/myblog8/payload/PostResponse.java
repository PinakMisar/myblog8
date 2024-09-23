package com.myblog8.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse
{
    private List<PostDto> content;
    private  int PageNo;
    private int PageSize;
    private int TotalElements;
    private int TotalPages;
    private boolean last;


}
