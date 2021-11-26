package com.shoppingcart.service.impl;

import com.shoppingcart.dto.cartDto.AddToCartDto;
import com.shoppingcart.dto.cartDto.CartDto;
import com.shoppingcart.dto.cartDto.CartItemDto;
import com.shoppingcart.exception.CustomException;
import com.shoppingcart.model.Cart;
import com.shoppingcart.model.CartKey;
import com.shoppingcart.model.Product;
import com.shoppingcart.model.User;
import com.shoppingcart.repository.CartRepo;
import com.shoppingcart.repository.ProductRepo;
import com.shoppingcart.repository.UserRepo;
import com.shoppingcart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    private final ProductRepo productRepo;

    private final UserRepo userRepo;

    @Override
    public void addToCart(AddToCartDto addToCartDto) throws CustomException {

        User user = userRepo.getById(addToCartDto.getUserId());
        Optional<Product> product = productRepo.findById(addToCartDto.getProductId());
       if(product.isEmpty()){
           throw new CustomException("no product is found of this id");
       }
        int quantity = product.get().getQuantity() - addToCartDto.getQuantity();
        if (quantity < 0) {
            String availableQuantity = Integer.toString(product.get().getQuantity());
            String message = "Quantity is not adequate. Quantity available is: ";
            message = message + availableQuantity;

            throw new CustomException(message);
        }

        product.get().setQuantity(quantity);
        productRepo.save(product.get());
        Cart cart = new Cart();
        CartKey cartKey= new CartKey();
        cartKey.setProductId(addToCartDto.getProductId());
        cartKey.setUserId(addToCartDto.getUserId());
        cart.setCartId(cartKey);

        cart.setProduct(product.get());
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setUser(user);
        cartRepo.save(cart);
    }

    @Override
    public CartDto listCartItems(User user) {

        List<Cart> cartList = cartRepo.findAllByUser(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getCartItemDto(cart);
            cartItems.add(cartItemDto);
        }
        int totalCost = 0;
        for (CartItemDto cartItemDto : cartItems) {
            totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        return new CartDto(cartItems, totalCost);
    }


    private CartItemDto getCartItemDto(Cart cart) {

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setQuantity(cart.getQuantity());
        Product product = cart.getProduct();
        product.setQuantity(cart.getQuantity());
        cartItemDto.setProduct(product);

        return cartItemDto;
    }


    @Override
    public void updateCartItem(AddToCartDto addToCartDto) throws CustomException {

        User user = userRepo.getById(addToCartDto.getUserId());
        Product product = productRepo.getById(addToCartDto.getProductId());
        Cart cart = cartRepo.findByUserAndProduct(user, product);
        int quantity = product.getQuantity() + cart.getQuantity() - addToCartDto.getQuantity();

        if (quantity < 0) {
            int remainingQuantity = product.getQuantity() - cart.getQuantity();
            String availableQuantity = Integer.toString(remainingQuantity);
            String message = "Quantity is not adequate. Number of products remaining in stock: ";
            message = message + availableQuantity;
            throw new CustomException(message);

        }
        product.setQuantity(quantity);
        cart.setQuantity(addToCartDto.getQuantity());
        cartRepo.save(cart);
    }


    @Override
    public void deleteUserCartItems(int id, int userId) {

        Product product = productRepo.getById(id);
        User user = userRepo.getById(userId);

        Cart cart = cartRepo.findByUserAndProduct(user,product);
        int productQuantity = product.getQuantity()+ cart.getQuantity();
        product.setQuantity(productQuantity);
        cartRepo.deleteByUserAndProduct(user,product);
    }


    @Override
    public void deleteUserCartItems(User user) {

        cartRepo.deleteByUser(user);
    }
}





