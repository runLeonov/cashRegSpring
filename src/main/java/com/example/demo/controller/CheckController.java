package com.example.demo.controller;

import com.example.demo.entity.ProductInStore;
import com.example.demo.service.CheckIdService;
import com.example.demo.service.CheckWithProductsService;
import com.example.demo.service.ProductsInStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("check")
public class CheckController {
    @Autowired
    ProductsInStoreService productsService;
    @Autowired
    CheckWithProductsService checkService;
    @Autowired
    CheckIdService checkIdService;
//    List<ProductInStore> products = new Model().;

    @GetMapping
    public String showCheck(HttpServletRequest model) {
        @SuppressWarnings("unchecked")
        List<ProductInStore> products =
                (ArrayList<ProductInStore>) model.getSession().getAttribute("productss");
        if (products == null)
            products = new ArrayList<>();
//        products.add(new ProductInStore(1L, "dsa", 78.0, 92.0));
        model.getSession().setAttribute("productss", products);
        return "check";
    }

    @PostMapping("addToCheck")
    public String addToCheck(
            @RequestParam(name = "idProd") Long id,
//            @RequestParam(name = "nameOfProd") String nameOfProd,
            @RequestParam(name = "weightProd") Double weight,
            HttpServletRequest model) {
        @SuppressWarnings("unchecked")
        List<ProductInStore> products =
                (ArrayList<ProductInStore>) model.getSession().getAttribute("productss");
        if (products == null)
            products = new ArrayList<>();

        ProductInStore product = productsService.findById(id);
        product.setPriceForOne(weight * product.getPriceForOne());
        product.setWeight(weight);
        products.add(product);
//        System.out.println(products.get(0));
        model.getSession().setAttribute("productss", products);
        return "redirect:/check";
    }

    @PostMapping("createCheck")
    public String createCheck(HttpServletRequest model) {
        @SuppressWarnings("unchecked")
        List<ProductInStore> products =
                (ArrayList<ProductInStore>) model.getSession().getAttribute("productss");
        checkIdService.incrementCheckId();
        if (products != null && products.size() != 0)
            checkService.createCheck(products);
        model.getSession().setAttribute("productss", null);
        return "redirect:/check";
    }

    @PostMapping("clearCheck")
    public String clearCheck(HttpServletRequest model) {
        model.getSession().setAttribute("productss", null);
        return "redirect:/check";
    }


}
