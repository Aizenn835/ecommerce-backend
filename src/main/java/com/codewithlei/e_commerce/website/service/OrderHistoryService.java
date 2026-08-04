package com.codewithlei.e_commerce.website.service;

import com.codewithlei.e_commerce.website.dto.OrderHistoryDTO.RequestHistoryDTO;

public interface OrderHistoryService {
    void purchaseItem(String email , RequestHistoryDTO request);
}
