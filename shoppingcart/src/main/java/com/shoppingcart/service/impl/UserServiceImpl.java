package com.shoppingcart.service.impl;

import com.shoppingcart.dto.cartDto.UserIdDto;
import com.shoppingcart.dto.userDto.SignInDto;
import com.shoppingcart.dto.userDto.SignUpDto;
import com.shoppingcart.exception.CustomException;
import com.shoppingcart.model.User;
import com.shoppingcart.repository.UserRepo;
import com.shoppingcart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    @Override
    public void signUp(SignUpDto signUpDto) throws CustomException {

        if(Objects.nonNull(userRepo.findByEmail(signUpDto.getEmail()))){

            throw new CustomException("User With this EmailId Already Exist");
        }

        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        userRepo.save(user);

    }

    @Override
    public UserIdDto signIn(SignInDto signInDto) throws CustomException{

        User user = userRepo.findByEmail(signInDto.getEmail());

        if(!Objects.nonNull(user)){
            throw new CustomException("User not found");
        }
        if(!user.getPassword().equals(signInDto.getPassword())) {
            throw new CustomException("Incorrect email/password");
        }

        UserIdDto userIdDto = new UserIdDto();
        userIdDto.setId(user.getId());

        return userIdDto;
    }


    @Override
    public User findById(int userId) throws CustomException{

        return userRepo.getById(userId);
    }

}
