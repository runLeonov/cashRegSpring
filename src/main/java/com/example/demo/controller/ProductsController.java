package com.example.demo.controller;


import com.example.demo.entity.ProductInStore;
import com.example.demo.repos.ProductInStoreRepo;
import com.example.demo.service.ProductsInStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
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
    public ModelAndView updateProduct(
            @RequestParam(name = "idProd", defaultValue = "1") Long id,
            @RequestParam(name = "priceProd", defaultValue = "50") Double price,
            @RequestParam(name = "weightProd", defaultValue = "100") Double weight,
            ModelMap modelMap
    ) {
        if (Objects.nonNull(service.findById(id))) {
            service.updateProductById(id, price, weight);
            modelMap.addAttribute("updatedP", true);
        } else {
            modelMap.addAttribute("updatedP", false);
        }
        return new ModelAndView("redirect:/products", modelMap);
    }

    @PostMapping("add")
    public ModelAndView addProduct(
            @RequestParam(name = "nameProdNew", defaultValue = "EmptyProduct!") String name,
            @RequestParam(name = "priceProdNew", defaultValue = "50") Double price,
            @RequestParam(name = "weightProdNew", defaultValue = "100") Double weight,
            ModelMap modelMap
    ) {
        modelMap.addAttribute("addedP", true);
        repo.save(new ProductInStore(name, price, weight));
        return new ModelAndView("redirect:/products", modelMap);
    }

    @PostMapping("delete")
    public ModelAndView deleteProduct(
            @RequestParam(name = "idProdToDel", defaultValue = "100") Long id,
            ModelMap modelMap
    ) {
        if (Objects.nonNull(service.findById(id))) {
            service.deleteProductInStoreById(id);
            modelMap.addAttribute("deleteP", true);
        } else {
            modelMap.addAttribute("deleteP", false);
        }
        return new ModelAndView("redirect:/products", modelMap);
    }

}
