package com.shoppingcart.dto.userDto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private @NotNull String firstName;

    private @NotNull String lastName;

    private @NotNull String email;

    private @NotNull String password;

}
