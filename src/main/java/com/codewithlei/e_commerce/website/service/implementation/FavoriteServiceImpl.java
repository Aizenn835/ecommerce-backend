package com.codewithlei.e_commerce.website.service.implementation;

import com.codewithlei.e_commerce.website.dto.favorite.ResponseFavoriteDTO;
import com.codewithlei.e_commerce.website.exception.FavoriteException.FavoriteNotFoundException;
import com.codewithlei.e_commerce.website.exception.ProductException.ProductNotFoundException;
import com.codewithlei.e_commerce.website.exception.UserException.UserNotFoundException;
import com.codewithlei.e_commerce.website.mapper.FavoriteMapper;
import com.codewithlei.e_commerce.website.model.entity.CartEntity;
import com.codewithlei.e_commerce.website.model.entity.FavoriteEntity;
import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.entity.UserEntity;
import com.codewithlei.e_commerce.website.repository.CartRepository;
import com.codewithlei.e_commerce.website.repository.FavoriteRepository;
import com.codewithlei.e_commerce.website.repository.ProductRepository;
import com.codewithlei.e_commerce.website.repository.UserRepository;
import com.codewithlei.e_commerce.website.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Primary
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final FavoriteMapper favoriteMapper;

    @Override
    public void toggleFavorite(String email , Long id){
         if(favoriteRepository.existsByUser_EmailAndProduct_Id(email , id)){
             favoriteRepository.deleteByUser_EmailAndProduct_Id(email , id);
         }else{
             UserEntity userDetails = userRepository.findByEmail(email)
                     .orElseThrow(UserNotFoundException::new);
             ProductEntity productDetails = productRepository.findById(id)
                     .orElseThrow(ProductNotFoundException::new);

             FavoriteEntity favorite = FavoriteEntity.builder()
                     .user(userDetails)
                     .product(productDetails)
                     .time(LocalTime.now())
                     .build();
             favoriteRepository.save(favorite);
         }
    }
    @Override
    public List<ResponseFavoriteDTO> getFavorite(String email){
         UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
         return favoriteRepository.findByUser(user)
                 .stream()
                 .map(favoriteMapper::mapToFavoriteDTO)
                 .toList();
    }
    @Override
    public void deleteFavorite(String email , Long productId){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        FavoriteEntity favorite = favoriteRepository.findByUser_EmailAndProduct_Id(user.getEmail() , productId)
                        .orElseThrow(FavoriteNotFoundException::new);
        favoriteRepository.delete(favorite);

    }

}
