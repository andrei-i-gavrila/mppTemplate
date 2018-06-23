package ro.kiuny.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Integer itemCount;

    private List<CartProductDto> products;

    private String email;

    private String address;
}
