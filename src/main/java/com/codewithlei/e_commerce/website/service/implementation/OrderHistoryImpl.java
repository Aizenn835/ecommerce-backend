package com.codewithlei.e_commerce.website.service.implementation;


import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseHistoryDTO;
import com.codewithlei.e_commerce.website.dto.orderHistoryDTO.ResponseOrderSummaryDTO;
import com.codewithlei.e_commerce.website.exception.userException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.OrderMapper;
import com.codewithlei.e_commerce.website.model.entity.OrderHistoryEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.OrderHistoryRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.OrderHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderHistoryImpl implements OrderHistoryService {
    private final UserRepository userRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<ResponseHistoryDTO> getOrderHistory(String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return orderHistoryRepository.findByUserOrderByOrderDateAsc(user)
                .stream()
                .map(orderMapper::mapToDTO)
                .toList();
    }


}
