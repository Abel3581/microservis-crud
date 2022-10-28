package com.crud.carritoservice.dto;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalTime;
import java.util.Date;
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
