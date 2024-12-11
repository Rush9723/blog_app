package com.blog.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotBlank(message = "Title should not be empty")
    private String categoryTitle;
    @NotBlank(message = "Description should not be empty")
    private String categoryDescription;
}
