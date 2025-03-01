package com.project.ecommerce.api.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(@Autowired ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

}
