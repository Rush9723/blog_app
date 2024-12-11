package com.blog.app.repositories;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(UserEntity user);

    List<Post> findByCategory_CategoryId(Category category);

    // This method is checked the title is contained or not in post if contains then return list<Post> otherWise return empty
    // This method is used to search for posts where the [title] contains the given string, meaning it performs a partial match. If any post’s title contains the provided string anywhere in it, that post will be included in the result.
    List<Post> findByTitleContaining(String title);


    // This method check the accurate title of post if same then return otherwise []
    List<Post> findByTitle(String title);

    //     Write query manually for search a post by title
    //    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    //    List<Post> searchByName(String keyword);
   // @Query("SELECT p FROM Post p WHERE p.category.categoryId = :categoryId")
    Page<Post> findByCategory_CategoryId(Integer categoryId, Pageable pageable);

//    Page<Post> findAllByCategoryId(Integer categoryId, Pageable pageable);




}
