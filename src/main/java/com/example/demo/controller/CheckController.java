package com.example.demo.controller;

import com.example.demo.entity.ProductInStore;
import com.example.demo.service.CheckIdService;
import com.example.demo.service.CheckWithProductsService;
import com.example.demo.service.ProductsInStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@PreAuthorize("hasAnyRole('ROLE_SENIOR', 'ROLE_CASHIER')")
@RequestMapping("check")
public class CheckController {
    @Autowired
    ProductsInStoreService productsService;
    @Autowired
    CheckWithProductsService checkService;
    @Autowired
    CheckIdService checkIdService;

    @GetMapping
    public String showCheck(HttpServletRequest model) {
        @SuppressWarnings("unchecked")
        List<ProductInStore> products =
                (ArrayList<ProductInStore>) model.getSession().getAttribute("productss");
        if (Objects.isNull(products))
            products = new ArrayList<>();
        model.getSession().setAttribute("productss", products);
        return "check";
    }

    @PostMapping("addToCheck")
    public ModelAndView addToCheck(
            @RequestParam(name = "idProd", defaultValue = "1") Long id,
            @RequestParam(name = "weightProd", defaultValue = "1") Double weight,
            HttpServletRequest model,
            ModelMap modelMap
    ) {
        @SuppressWarnings("unchecked")
        List<ProductInStore> products =
                (ArrayList<ProductInStore>) model.getSession().getAttribute("productss");
        if (Objects.isNull(products))
            products = new ArrayList<>();

        ProductInStore product = productsService.findById(id);
        if (Objects.nonNull(product) && productsService.addProductCheck(product, weight)) {
            productsService.updateWeightInStore(id, weight);
            product.setPriceForOne(weight * product.getPriceForOne());
            product.setWeight(weight);
            products.add(product);
            model.getSession().setAttribute("productss", products);
            modelMap.addAttribute("added", true);
        } else {
            modelMap.addAttribute("added", false);
        }
        return new ModelAndView("redirect:/check", modelMap);
    }

    @PostMapping("createCheck")
    public ModelAndView createCheck(HttpServletRequest model, ModelMap modelMap) {
        @SuppressWarnings("unchecked")
        List<ProductInStore> products =
                (ArrayList<ProductInStore>) model.getSession().getAttribute("productss");
        checkIdService.incrementCheckId();
        if (Objects.nonNull(products) && products.size() != 0) {
            checkService.createCheck(products);
            modelMap.addAttribute("created", true);
        }
        model.getSession().setAttribute("productss", null);
        return new ModelAndView("redirect:/check", modelMap);
    }

    @PostMapping("clearCheck")
    public String clearCheck(HttpServletRequest model) {
        model.getSession().setAttribute("productss", null);
        return "redirect:/check";
    }


}
