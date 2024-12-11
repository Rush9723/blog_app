package com.blog.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity

@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "categoryTitle", nullable = false)
    private String categoryTitle;
    @Column(name = "description", nullable = false)
    private String categoryDescription;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
