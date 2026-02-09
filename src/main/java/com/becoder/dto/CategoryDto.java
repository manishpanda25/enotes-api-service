package com.becoder.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
	private Integer id;
	
	@NotBlank(message = "Name is required")
	@Size(min = 10, max = 100, message = "Name must be between 10 and 100 characters")
	private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters")
    private String description;

    @NotNull(message = "Active status is required")
    private Boolean active;
	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updatedOn;

}
