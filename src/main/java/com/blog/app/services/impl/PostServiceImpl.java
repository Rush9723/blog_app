package com.blog.app.services.impl;

import com.blog.app.dto.PostDto;
import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.UserEntity;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.PostResponse;
import com.blog.app.repositories.CategoryRepository;
import com.blog.app.repositories.PostRepository;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.PostService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter
@Getter
@NoArgsConstructor
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
        Post postEntity = this.modelMapper.map(postDto, Post.class);
        if (postDto.getImageName() == null) {
            postEntity.setImageName("default.png");
        }
        if (postDto.getAddedDate() == null) {
            postEntity.setAddedDate(new Date());
        }
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category categoryEntity = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        postEntity.setUser(userEntity);
        postEntity.setCategory(categoryEntity);
        return this.modelMapper.map(this.postRepository.save(postEntity), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());
        postEntity.setImageName(postDto.getImageName());
        return this.modelMapper.map(this.postRepository.save(postEntity), PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        this.postRepository.delete(postEntity);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> allPostEntities = postRepository.findAll();
        return allPostEntities.stream().map((postEntity) -> this.modelMapper.map(postEntity, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        return this.modelMapper.map(postEntity, PostDto.class);
    }


    // Get all post by category
    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category categoryEntity = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        List<Post> postEntities = this.postRepository.findByCategory_CategoryId(categoryEntity);
        return postEntities.stream().map((postEntity) -> this.modelMapper.map(postEntity, PostDto.class)).collect(Collectors.toList());
    }

    // Get all posts by category pagination
    @Override
    public PostResponse pageOfPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize){
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost= this.postRepository.findByCategory_CategoryId(categoryId,pageable);
        List<Post> pagePostList= pagePost.getContent();
        List<PostDto> pagePostDto = pagePostList.stream().map((postEntity)-> this.modelMapper.map(postEntity,PostDto.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContents(pagePostDto);
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }
    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Post> postEntities = this.postRepository.findByUser(userEntity);
        return postEntities.stream().map((postEntity) -> this.modelMapper.map(postEntity, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> searchedPosts = this.postRepository.findByTitleContaining(keyword);
        return searchedPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostResponse pageOfPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        // firstly convert into pageable object
        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p =PageRequest.of(pageNumber,pageSize, sort);
        // get page by pageable object ,It returns the one page with number of posts
        Page<Post> pagePost= this.postRepository.findAll(p);
        // get content from page i.e. get all post from this page
        List<Post> pagePostList = pagePost.getContent();
        // convert the list of postEntity to list of postDto and return it
        List<PostDto> postDtoList= pagePostList.stream().map((postEntity)-> this.modelMapper.map(postEntity, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse= new PostResponse();
        postResponse.setContents(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }


}
