package com.shoppingcart.controller;


import com.shoppingcart.common.ApiResponse;
import com.shoppingcart.dto.cartDto.UserIdDto;
import com.shoppingcart.dto.userDto.SignInDto;
import com.shoppingcart.dto.userDto.SignUpDto;
import com.shoppingcart.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserServiceImpl userService;

    @PostMapping ("/signUp")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpDto signUpDto){

        userService.signUp(signUpDto);

        return new ResponseEntity<>(new ApiResponse(true,"User Successfully Created"), HttpStatus.CREATED);

    }

    @PostMapping ("/signIn")
    public ResponseEntity<UserIdDto> signIn(@RequestBody SignInDto signInDto){

       UserIdDto userIdDto =  userService.signIn(signInDto);

        return new ResponseEntity<>(userIdDto, HttpStatus.OK);

    }

}
