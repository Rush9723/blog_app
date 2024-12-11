package com.blog.app.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
    private String title;
    private  String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;



}
