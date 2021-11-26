package com.shoppingcart.controller;


import com.shoppingcart.common.ApiResponse;
import com.shoppingcart.dto.cartDto.AddToCartDto;
import com.shoppingcart.dto.cartDto.CartDto;
import com.shoppingcart.model.User;
import com.shoppingcart.service.impl.CartServiceImpl;
import com.shoppingcart.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartServiceImpl cartService;

    private final UserServiceImpl userService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto) {

         cartService.addToCart(addToCartDto);

         return new ResponseEntity<>(new ApiResponse(true, "Successfully added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<CartDto> getCartItems(@RequestParam int userId){

        User user = userService.findById(userId);
        CartDto cartDto = cartService.listCartItems(user);

        return new ResponseEntity<>(cartDto,HttpStatus.OK);

    }

    @PutMapping("/updateCart")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody AddToCartDto addToCartDto){

       cartService.updateCartItem(addToCartDto);

       return new ResponseEntity<>(new ApiResponse(true,"Successfully Updated"),HttpStatus.OK);

    }

   @DeleteMapping("/delete/{userId}/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int cartItemId, @PathVariable("userId") int userId){

        cartService.deleteUserCartItems(cartItemId, userId);

        return new ResponseEntity<>(new ApiResponse(true,"Successfully Deleted"),HttpStatus.OK);
    }

}
