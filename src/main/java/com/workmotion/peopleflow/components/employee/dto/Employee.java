package com.workmotion.peopleflow.components.employee.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Mahmoud Sakr - mahmoudragab726@gmail.com
 * @Created: @ 7/12/2021 by OLE
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Min(18) @Max(60)
    private Integer age;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String title;
    private String state;
    private String address;
}
