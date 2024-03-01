package com.orazov.msbtrust.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyUserDto {
    private Long id;
    private String username;
    private String email;
}
