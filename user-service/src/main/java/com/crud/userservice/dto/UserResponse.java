package com.crud.userservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
}
