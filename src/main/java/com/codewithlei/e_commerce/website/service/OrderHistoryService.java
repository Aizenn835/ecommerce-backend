package com.codewithlei.e_commerce.website.service;


import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseHistoryDTO;

import java.util.List;

public interface OrderHistoryService {
    void purchaseItem(String email);
    List<ResponseHistoryDTO> getOrderHistory(String email);
 }