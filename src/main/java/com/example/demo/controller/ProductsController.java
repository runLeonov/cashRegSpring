package com.example.demo.controller;


import com.example.demo.entity.ProductInStore;
import com.example.demo.repos.ProductInStoreRepo;
import com.example.demo.service.ProductsInStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("products")
public class ProductsController {
    @Autowired
    private ProductInStoreRepo repo;
    @Autowired
    private ProductsInStoreService service;

    @GetMapping
    public String showProducts(Model model) {
        Iterable<ProductInStore> products = repo.findAll();
        model.addAttribute("productss", products);
        return "products";
    }


    @PostMapping("update")
    public String updateProduct(
            @RequestParam(name = "idProd") Long id,
            @RequestParam(name = "priceProd", defaultValue = "10") Double price,
            @RequestParam(name = "weightProd", defaultValue = "100") Double weight,
            Model model
    ) {
        service.updateProductById(id, price, weight);
        Iterable<ProductInStore> products = repo.findAll();
        model.addAttribute("productss", products);
        return "redirect:/products";
    }

    @PostMapping("add")
    public String addProduct(
            @RequestParam(name = "nameProdNew") String name,
            @RequestParam(name = "priceProdNew", defaultValue = "10") Double price,
            @RequestParam(name = "weightProdNew", defaultValue = "100") Double weight,
            Model model
    ) {
        repo.save(new ProductInStore(name, price, weight));
        Iterable<ProductInStore> products = repo.findAll();
        model.addAttribute("productss", products);
        return "redirect:/products";
    }

    @PostMapping("delete")
    public String deleteProduct(
            @RequestParam(name = "idProdToDel") Long id,
            Model model
    ) {
        service.deleteProductInStoreById(id);
        Iterable<ProductInStore> products = repo.findAll();
        model.addAttribute("productss", products);
        return "redirect:/products";
    }
}
