package com.blog.app.controllers;

import com.blog.app.config.AppConstants;
import com.blog.app.dto.PostDto;
import com.blog.app.payloads.PostResponse;
import com.blog.app.services.PostService;
import com.blog.app.utils.ApiResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@Setter
@Getter
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/userId/{userId}/categoryId/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
//        PostDto savedPostDto = postService.createPost(postDto, categoryId, userId);
        return new ResponseEntity<PostDto>(postService.createPost(postDto, categoryId, userId), HttpStatus.CREATED);

    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
//        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, postId), HttpStatus.CREATED);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
//        List<PostDto> postDtoList = this.postService.getAllPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(this.postService.getAllPostByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        return new ResponseEntity<List<PostDto>>(this.postService.getAllPostByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<List<PostDto>>(this.postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return new ResponseEntity<PostDto>(this.postService.getPostById(postId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        log.info("Delete post method is calling...");
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.POST_DELETED_SUCCESSFULLY_TITLE, true), HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
        return new ResponseEntity<List<PostDto>>(this.postService.searchPosts(keyword), HttpStatus.OK);

    }

    @GetMapping("/page")
    public ResponseEntity<PostResponse> pageOfPost(
            @RequestParam(value = AppConstants.PAGE_NUMBER_TITLE, defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value =AppConstants.PAGE_SIZE_TITLE, defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = AppConstants.SORT_BY_TITLE, defaultValue =AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = AppConstants.SORT_DIR_TITLE, defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ) {

        return new ResponseEntity<PostResponse>(this.postService.pageOfPost(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/page/category/{categoryId}")
    public ResponseEntity<PostResponse> pageOfPostByCategory(@PathVariable Integer categoryId, @RequestParam(value =AppConstants.PAGE_NUMBER_TITLE, defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(value = AppConstants.PAGE_SIZE_TITLE, defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
        return new ResponseEntity<PostResponse>(this.postService.pageOfPostByCategory(categoryId, pageNumber, pageSize), HttpStatus.OK);
    }
}
