package com.blog.app.services;

import com.blog.app.dto.PostDto;
import com.blog.app.entities.Post;
import com.blog.app.payloads.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPosts();

    PostDto getPostById(Integer postId);

    // get all post by category
    List<PostDto> getAllPostByCategory(Integer categoryId);

    // get all posts by user
    List<PostDto> getAllPostByUser(Integer userId);

    // search post by keyword
    List<PostDto> searchPosts(String keyword);

    PostResponse pageOfPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostResponse pageOfPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);
}
