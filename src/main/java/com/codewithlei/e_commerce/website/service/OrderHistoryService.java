package com.codewithlei.e_commerce.website.service;


import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseHistoryDTO;
import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseOrderConfirmationDTO;
import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseOrderSummaryDTO;

import java.util.List;

public interface OrderHistoryService {
    List<ResponseHistoryDTO> getOrderHistory(String email);
}