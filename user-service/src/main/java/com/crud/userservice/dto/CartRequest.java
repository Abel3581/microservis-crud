package com.crud.userservice.dto;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartRequest {

    private boolean state;
    private Date createDate;
    private Long userId;
}
