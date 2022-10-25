package com.crud.userservice.dto;

import lombok.*;

import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartResponse {

    private Long id;
    private boolean state;
    private LocalTime createDate;
    private Long userId;
}
