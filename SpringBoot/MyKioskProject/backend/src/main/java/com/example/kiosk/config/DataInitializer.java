package com.example.kiosk.config;

import com.example.kiosk.entity.Member;
import com.example.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final MemberRepository memberRepository;
    private final com.example.kiosk.repository.MenuRepository menuRepository;
    private final com.example.kiosk.repository.CategoryRepository categoryRepository;

    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {
            // Admin creation logic removed by request

            // Init Categories
            if (categoryRepository.count() == 0) {
                categoryRepository.save(com.example.kiosk.entity.Category.builder().name("커피").displayOrder(1).build());
                categoryRepository.save(com.example.kiosk.entity.Category.builder().name("음료").displayOrder(2).build());
                categoryRepository
                        .save(com.example.kiosk.entity.Category.builder().name("디저트").displayOrder(3).build());
                System.out.println("Default categories created.");
            }

            // Init Menus from images
            String[] menuImages = {
                    "americano.png", "b-latte.png", "caramel-latte.png", "choke-bang.png",
                    "d-cake.png", "d-latte.png", "k-latte.png", "k-moka.png",
                    "kafuchino.png", "krongji.png", "m-cake.png", "milk-cake.png",
                    "r-aid.png", "r-bang.png", "rice-latte.png", "sicke.png",
                    "tiramishu.png", "y-latte.png"
            };

            for (String img : menuImages) {
                String imageUrl = "/images/menu/" + img;
                if (menuRepository.findByImageUrl(imageUrl).isEmpty()) {
                    Member adminUser = memberRepository.findByUsername("관리자").orElse(null);
                    com.example.kiosk.entity.Menu menu = com.example.kiosk.entity.Menu.builder()
                            .name("New Menu")
                            .price(0)
                            .description("")
                            .imageUrl(imageUrl)
                            .category(null) // No category initially
                            .memberIds(adminUser != null ? String.valueOf(adminUser.getId()) : "1")
                            .build();
                    menuRepository.save(menu);
                    System.out.println("Created menu for: " + img);
                }
            }
        };
    }
}
