package com.example.demo.controller;

import com.example.demo.entity.CheckId;
import com.example.demo.entity.CheckWithProducts;
import com.example.demo.repos.CheckWithProductsRepo;
import com.example.demo.service.CheckIdService;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Controller
@PreAuthorize("hasRole('ROLE_SENIOR')")
@RequestMapping("cancel")
public class CancelController {
    @Autowired
    CheckIdService checkIdService;
    @Autowired
    CheckWithProductsRepo checkWithProductsRepo;
    @Autowired
    ReportService reportService;

    @GetMapping
    public String showCheck() {
        return "cancel";
    }

    @PostMapping("deleteById")
    public ModelAndView deleteByIdCheck(
            @RequestParam(name = "idProd", defaultValue = "100") Long id,
            HttpServletRequest model,
            ModelMap modelMap) {

        CheckId checkId = checkIdService.findById(id);
        if (Objects.nonNull(checkId)) {
            modelMap.addAttribute("checkDeleted", true);
            isTheSameChecks(model, checkId);
            checkIdService.deleteCheck(checkId);
        } else {
            modelMap.addAttribute("checkDeleted", false);
        }
        return new ModelAndView("redirect:/cancel", modelMap);
    }

    @PostMapping("deleteLast")
    public ModelAndView deleteLastCheck(
            HttpServletRequest model,
            ModelMap modelMap) {
        CheckWithProducts checkWithProdsId = checkWithProductsRepo.getFirstByOrderByCheckIdDesc();
        if (Objects.nonNull(checkWithProdsId)) {
            CheckId checkId = checkIdService.findById(checkWithProdsId.getCheckId());
            modelMap.addAttribute("checkDeletedLast", true);
            isTheSameChecks(model, checkId);
            checkIdService.deleteCheck(checkId);
            return new ModelAndView("redirect:/cancel", modelMap);
        }
        modelMap.addAttribute("checkDeletedLast", false);
        return new ModelAndView("redirect:/cancel", modelMap);

    }

    @PostMapping("showLast")
    public ModelAndView showLastCheck(HttpServletRequest model, ModelMap modelMap) {
        CheckWithProducts checkWithProdsId = checkWithProductsRepo.getFirstByOrderByCheckIdDesc();
        if (Objects.nonNull(checkWithProdsId)) {
            CheckId checkId = checkIdService.findById(checkWithProdsId.getCheckId());
            showCheck(checkId, model);
            return new ModelAndView("redirect:/cancel", modelMap);
        }
        modelMap.addAttribute("noChecks", true);
        return new ModelAndView("redirect:/cancel", modelMap);
    }

    @PostMapping("showById")
    public ModelAndView showByIdCheck(
            @RequestParam(name = "idProd", defaultValue = "100") Long id,
            HttpServletRequest model,
            ModelMap modelMap) {
        CheckId checkId = checkIdService.findById(id);
        if (Objects.nonNull(checkId) && !checkId.getProducts().isEmpty()) {
            showCheck(checkId, model);
        } else {
            modelMap.addAttribute("checkFound", false);
        }
        return new ModelAndView("redirect:/cancel", modelMap);
    }


    @PostMapping("createX")
    public String createX(Model model) {
        model.addAttribute("report", reportService.createXReport());
        return "reportX";
    }

    @PostMapping("createZ")
    public String createZ(Model model) {
        model.addAttribute("report", reportService.createZReport());
        return "reportZ";
    }


    private void showCheck(CheckId checkId, HttpServletRequest model) {
        if (Objects.nonNull(checkId) && !checkId.getProducts().isEmpty()) {
            model.getSession().setAttribute("check", checkId);
            LocalDateTime time = checkId.getProducts().get(0).getDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            model.getSession().setAttribute("dateCreate", formatter.format(time));
        }
    }

    private void isTheSameChecks(HttpServletRequest model, CheckId checkId) {
        if (Objects.nonNull(checkId) && checkId.equals(model.getSession().getAttribute("check"))) {
            model.getSession().setAttribute("check", null);
        }
    }
}
