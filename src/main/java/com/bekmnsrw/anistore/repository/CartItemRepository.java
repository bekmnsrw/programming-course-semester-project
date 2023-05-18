package com.bekmnsrw.anistore.repository;

import com.bekmnsrw.anistore.model.CartItem;
import com.bekmnsrw.anistore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    @Query(value = "SELECT c.product FROM cart_items c WHERE c.cart.id = :cartId order by c.product.id")
    List<Product> getAllProductsIdsInCart(Long cartId);

    @Query(value = "SELECT c.productAmount FROM cart_items c WHERE c.cart.id = :cartId order by c.product.id")
    List<Long> getAllProductsAmountInCart(Long cartId);

    @Query(value = "UPDATE cart_items c SET c.productAmount = c.productAmount + 1 WHERE c.cart.id = :cartId AND c.product.id = :productId")
    void increaseProductAmount(Long cartId, Long productId);

    @Query(value = "UPDATE cart_items c SET c.productAmount = c.productAmount - 1 WHERE c.cart.id = :cartId AND c.product.id = :productId")
    void decreaseProductAmount(Long cartId, Long productId);
}
