package com.crud.userservice.dto;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.lang.Nullable;

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
