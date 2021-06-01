package com.example.demo.controller;

import com.example.demo.entity.CheckId;
import com.example.demo.service.CheckIdService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("cancel")
public class CancelController {
    @Autowired
    CheckIdService checkIdService;

    @GetMapping
    public String showCheck(Model model) {
        return "cancel";
    }

    @PostMapping("deleteById")
    public String deleteByIdCheck(
            @RequestParam(name = "idProd") Long id,
            HttpServletRequest model) {
        CheckId checkId = checkIdService.findById(id);
        isTheSameChecks(model, checkId);
        checkIdService.deleteCheck(checkId);
        return "redirect:/cancel";
    }

    @PostMapping("deleteLast")
    public String deleteLastCheck(HttpServletRequest model) {
        @NotNull
        CheckId checkId = checkIdService.findLastCheck();
        isTheSameChecks(model, checkId);
        checkIdService.deleteCheck(checkId);
        return "redirect:/cancel";

    }

    @PostMapping("showLast")
    public String showLastCheck(HttpServletRequest model) {
        CheckId checkId = checkIdService.findLastCheck();
        showCheck(checkId, model);
        return "redirect:/cancel";
    }

    @PostMapping("showById")
    public String showByIdCheck(
            @RequestParam(name = "idProd") Long id,
            HttpServletRequest model) {
        CheckId checkId = checkIdService.findById(id);
        showCheck(checkId, model);
        return "redirect:/cancel";
    }


    private void showCheck(CheckId checkId, HttpServletRequest model) {
        model.getSession().setAttribute("check", checkId);
        if (checkId != null && !checkId.getProducts().isEmpty()) {
            LocalDateTime time = checkId.getProducts().get(0).getDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            model.getSession().setAttribute("dateCreate", formatter.format(time));
        }
    }

    private void isTheSameChecks(HttpServletRequest model, CheckId checkId) {
        if (checkId != null && checkId.equals(model.getSession().getAttribute("check"))) {
            model.getSession().setAttribute("check", null);
        }
    }
}
