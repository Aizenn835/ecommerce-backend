package com.codewithlei.e_commerce.website.configs;

import com.codewithlei.e_commerce.website.model.entity.ProductEntity;
import com.codewithlei.e_commerce.website.model.enums.Category;
import com.codewithlei.e_commerce.website.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args){
        seedProduct();
    }
    public void seedProduct(){
       if(productRepository.count() == 0){
           return;
       }
        List<ProductEntity> products = List.of(

                ProductEntity.builder()
                        .productName("Classic Leather Bag")
                        .price(new BigDecimal("2499.00"))
                        .productDescription("Crafted from premium genuine leather, this elegant handbag combines timeless style with everyday practicality. It features a spacious main compartment, secure zipper closure, and comfortable shoulder straps, making it perfect for work, shopping, travel, or casual outings. Designed to complement both formal and casual outfits.")
                        .category(Category.ACCESSORIES)
                        .color("Black")
                        .numberOfSold(124)
                        .stock(25)
                        .productSize("Medium")
                        .imgUrl("/uploads/bag.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Ceramic Coffee Mug")
                        .price(new BigDecimal("299.00"))
                        .productDescription("Enjoy your favorite hot or cold beverages with this high-quality ceramic coffee mug. Designed with a minimalist finish and a comfortable grip, it is microwave and dishwasher safe, making it suitable for everyday use at home, in the office, or while relaxing with your favorite drink.")
                        .category(Category.HOME)
                        .color("White")
                        .numberOfSold(310)
                        .stock(80)
                        .productSize("350ml")
                        .imgUrl("/uploads/cafe-mug.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Formal Office Shirt")
                        .price(new BigDecimal("899.00"))
                        .productDescription("Made from breathable and wrinkle-resistant fabric, this formal office shirt offers exceptional comfort throughout the day. Its tailored fit, durable stitching, and classic collar make it an excellent choice for business meetings, office wear, special occasions, and professional events.")
                        .category(Category.CLOTHING)
                        .color("White")
                        .numberOfSold(87)
                        .stock(35)
                        .productSize("Large")
                        .imgUrl("/uploads/formal-shirt.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Casual Jacket")
                        .price(new BigDecimal("1699.00"))
                        .productDescription("This lightweight casual jacket is designed to provide warmth without sacrificing comfort or style. Featuring premium fabric, durable zippers, multiple pockets, and a modern slim-fit design, it is perfect for daily wear, outdoor activities, and cool weather conditions.")
                        .category(Category.CLOTHING)
                        .color("Navy Blue")
                        .numberOfSold(54)
                        .stock(20)
                        .productSize("Large")
                        .imgUrl("/uploads/jacket.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Casual T-Shirt")
                        .price(new BigDecimal("499.00"))
                        .productDescription("Experience all-day comfort with this soft cotton t-shirt made from breathable and lightweight fabric. Designed for everyday wear, it features a modern fit that pairs well with jeans, shorts, or joggers, making it a versatile addition to any wardrobe.")
                        .category(Category.CLOTHING)
                        .color("Gray")
                        .numberOfSold(212)
                        .stock(65)
                        .productSize("Medium")
                        .imgUrl("/uploads/non-formal-shirt.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Winter Scarf")
                        .price(new BigDecimal("399.00"))
                        .productDescription("Stay warm and stylish during colder days with this knitted winter scarf. Crafted from soft, skin-friendly material, it provides excellent insulation while adding a fashionable touch to your outfit. Suitable for everyday use, travel, and outdoor activities.")
                        .category(Category.ACCESSORIES)
                        .color("Brown")
                        .numberOfSold(41)
                        .stock(30)
                        .productSize("One Size")
                        .imgUrl("/uploads/scarf.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Running Shoes")
                        .price(new BigDecimal("2199.00"))
                        .productDescription("Designed for athletes and fitness enthusiasts, these running shoes feature lightweight construction, breathable mesh material, and responsive cushioning that absorbs impact during every step. The durable rubber outsole provides excellent traction for running, walking, and everyday activities.")
                        .category(Category.FOOTWEAR)
                        .color("White")
                        .numberOfSold(173)
                        .stock(40)
                        .productSize("42")
                        .imgUrl("/uploads/shoes.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Smart Watch")
                        .price(new BigDecimal("3299.00"))
                        .productDescription("Track your health, monitor workouts, and stay connected with this modern smartwatch. It features heart rate monitoring, sleep tracking, Bluetooth notifications, multiple fitness modes, and a long-lasting rechargeable battery, making it the perfect companion for both work and active lifestyles.")
                        .category(Category.ACCESSORIES)
                        .color("Black")
                        .numberOfSold(91)
                        .stock(18)
                        .productSize("42mm")
                        .imgUrl("/uploads/smart-watch.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Polarized Sunglasses")
                        .price(new BigDecimal("799.00"))
                        .productDescription("Protect your eyes with these stylish polarized sunglasses featuring UV400 protection that blocks harmful ultraviolet rays. The lightweight frame, scratch-resistant lenses, and comfortable fit make them ideal for driving, outdoor adventures, beach trips, and everyday use.")
                        .category(Category.ACCESSORIES)
                        .color("Black")
                        .numberOfSold(145)
                        .stock(50)
                        .productSize("Standard")
                        .imgUrl("/uploads/sunglasses.avif")
                        .build(),

                ProductEntity.builder()
                        .productName("Modern Drinking Glass Set")
                        .price(new BigDecimal("599.00"))
                        .productDescription("This premium three-piece drinking glass set is crafted from durable, crystal-clear glass that enhances the presentation of your favorite beverages. Perfect for serving water, juice, soft drinks, or cocktails, it combines elegance, durability, and everyday functionality for any home.")
                        .category(Category.HOME)
                        .color("Transparent")
                        .numberOfSold(63)
                        .stock(45)
                        .productSize("3 Pieces")
                        .imgUrl("/uploads/three-glass.avif")
                        .build()

        );

        productRepository.saveAll(products);
    }
}
