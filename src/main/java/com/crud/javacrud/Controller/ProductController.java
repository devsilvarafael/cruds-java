package com.crud.javacrud.Controller;

import com.crud.javacrud.Model.Product;
import com.crud.javacrud.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping // Rota "GET" -> Consulta todos os produtos no banco.
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}") // Rota "GET" -> Consulta apenas o produto solicitado no banco.
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Optional<Product> productExists = productRepository.findById(id);

        if(productExists.isPresent()) {
            return ResponseEntity.ok(productExists);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping // Rota "POST" -> Cadastra um novo produto no banco.
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product newProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}") // Rota "PUT" -> Edita um produto já existente no banco, e altera seus dados.
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productExists = productRepository.findById(id);

        if(productExists.isPresent()) {
            Product updatedProduct = productExists.get();

            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());

            Product savedProduct = productRepository.save(updatedProduct);
            return ResponseEntity.ok(savedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // Rota "DELETE" -> Deletar um produto que já existe.
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
