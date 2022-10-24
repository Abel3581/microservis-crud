package com.crud.carritoservice.dto;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartRequest {

    private boolean state;
    private Date createDate;
    private Long userId;
}
