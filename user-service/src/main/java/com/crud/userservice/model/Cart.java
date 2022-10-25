package com.crud.userservice.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Cart {

    private boolean state;
    private Date createDate;
}
