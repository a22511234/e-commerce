package com.howard0720.ecommerce.controller;

import com.howard0720.ecommerce.constant.ProductCategory;
import com.howard0720.ecommerce.dto.ProductQueryParrams;
import com.howard0720.ecommerce.dto.ProductRequest;
import com.howard0720.ecommerce.model.Product;
import com.howard0720.ecommerce.service.ProductService;
import com.howard0720.ecommerce.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            //filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            //Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParrams productQueryParrams = new ProductQueryParrams();
        productQueryParrams.setCategory(category);
        productQueryParrams.setSearch(search);
        productQueryParrams.setOrderBy(orderBy);
        productQueryParrams.setSort(sort);
        productQueryParrams.setLimit(limit);
        productQueryParrams.setOffset(offset);

        List<Product> productList = productService.getProducts(productQueryParrams);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/productss")
    public ResponseEntity<Page<Product>> getProducts2(
            //filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            //Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParrams productQueryParrams = new ProductQueryParrams();
        productQueryParrams.setCategory(category);
        productQueryParrams.setSearch(search);
        productQueryParrams.setOrderBy(orderBy);
        productQueryParrams.setSort(sort);
        productQueryParrams.setLimit(limit);
        productQueryParrams.setOffset(offset);

        List<Product> productList = productService.getProducts(productQueryParrams);
        Integer total = productService.countProducts(productQueryParrams);
        Page<Product> page =new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        if (product != null){
            return  ResponseEntity.status(HttpStatus.OK).body(product);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createtProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);

        return  ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {
        Product product = productService.getProductById(productId);
        if (product != null){
            productService.updateProduct(productId,productRequest);
            Product updatedProduct = productService.getProductById(productId);
            return  ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){
            productService.deleteProductById(productId);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
