package com.sample.bootstrap;

import com.sample.repositories.ProductRepository;
import com.sample.domain.Product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;

    private Logger log = LogManager.getLogger(ProductLoader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product p1 = new Product();
        p1.setDescription("iPhone");
        p1.setPrice(new BigDecimal("699.95"));
        p1.setImageUrl("https://images/iphone.jpg");
        p1.setProductId("1");
        productRepository.save(p1);

        log.info("Saved iPhone - id: " + p1.getId());

        Product p2 = new Product();
        p2.setDescription("Android");
        p2.setImageUrl("https://images/android.jpg");
        p2.setProductId("2");
        p2.setPrice(new BigDecimal("699.95"));
        productRepository.save(p2);

        log.info("Saved Android - id:" + p2.getId());
    }
}
