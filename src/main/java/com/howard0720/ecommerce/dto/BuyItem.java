package com.howard0720.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public class BuyItem {
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quentity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuentity() {
        return quentity;
    }

    public void setQuentity(Integer quentity) {
        this.quentity = quentity;
    }
}
