package com.howard0720.ecommerce.service;

import com.howard0720.ecommerce.dto.CreateOderRequest;

public interface OderService {
    Integer creteOder(Integer userId, CreateOderRequest createOderRequest);
}
