package com.codewithlei.e_commerce.website.controller;

import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseHistoryDTO;

import com.codewithlei.e_commerce.website.service.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @GetMapping("/history")
    public List<ResponseHistoryDTO> orderHistory(Authentication authentication){
        return orderHistoryService.getOrderHistory(authentication.getName());
    }



}
