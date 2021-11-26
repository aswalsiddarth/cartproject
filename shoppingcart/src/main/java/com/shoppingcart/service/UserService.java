package com.shoppingcart.service;

import com.shoppingcart.dto.cartDto.UserIdDto;
import com.shoppingcart.dto.userDto.SignInDto;
import com.shoppingcart.dto.userDto.SignUpDto;
import com.shoppingcart.exception.CustomException;
import com.shoppingcart.model.User;

public interface UserService {

    void signUp(SignUpDto signUpDto) throws CustomException;

    UserIdDto signIn(SignInDto signInDto) throws CustomException;

    User findById(int UserId) throws CustomException;
}
