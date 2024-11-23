package com.teachCode.ecommerce1.dto.response;

public record DtoCart(
        Long id,

        String productName,

        String productId,

        String productImg,

        int productQuantity,

        int productPrice
) {



}



