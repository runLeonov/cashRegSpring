package com.example.demo.service;

import com.example.demo.entity.CheckId;
import com.example.demo.entity.CheckWithProducts;
import com.example.demo.entity.DeletedCheckWithProducts;
import com.example.demo.entity.Report;
import com.example.demo.repos.CheckIdRepo;
import com.example.demo.repos.CheckWithProductsRepo;
import com.example.demo.repos.DeletedCheckWithProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    CheckIdRepo checkIdRepo;
    @Autowired
    CheckWithProductsRepo checkWithProductsRepo;
    @Autowired
    DeletedCheckWithProductsRepo deletedCheckWithProductsRepo;


    public Report createXReport() {
        List<DeletedCheckWithProducts> d = (ArrayList<DeletedCheckWithProducts>)
                deletedCheckWithProductsRepo.findAll();
        List<CheckWithProducts> checkWithProducts = (ArrayList<CheckWithProducts>)
                checkWithProductsRepo.findAll();
        long deletedCheckCount = d.stream()
                .filter(x -> x.getTimeOfCreate().getDayOfWeek() == LocalDateTime.now().getDayOfWeek())
                .filter(x -> Collections.frequency(d, x.getCheckId()) == 1).count();
        long countChecks = checkWithProducts.stream()
                .filter(x -> x.getDateTime().getDayOfWeek() == LocalDateTime.now().getDayOfWeek())
                .filter(x -> Collections.frequency(d, x.getCheckId()) == 1).count();
        double totalA = checkWithProducts.stream().filter(x -> x.getDateTime().getDayOfWeek()
                == LocalDateTime.now().getDayOfWeek()).mapToDouble(CheckWithProducts::getPrice).sum();
        double totalB = 0.0;
        double totalC = 0.0;
        double totalSum = totalA + totalB + totalC;
        double ndsA = totalSum * 0.2;
        double ndsB = 0.0;
        double ndsC = 0.0;
        double totalNds = ndsA + ndsB + ndsC;
        return new Report(
                new Timestamp(System.currentTimeMillis()),
                countChecks,
                deletedCheckCount,
                totalA,
                totalB,
                totalC,
                totalSum,
                ndsA,
                ndsB,
                ndsC,
                totalNds
        );
    }

    public Report createZReport() {
        List<DeletedCheckWithProducts> d = (ArrayList<DeletedCheckWithProducts>)
                deletedCheckWithProductsRepo.findAll();
        List<CheckWithProducts> checkWithProducts = (ArrayList<CheckWithProducts>)
                checkWithProductsRepo.findAll();
        long deletedCheckCount = d.stream()
                .filter(x -> x.getTimeOfCreate().getMonth() == LocalDateTime.now().getMonth())
                .filter(x -> Collections.frequency(d, x.getCheckId()) == 1).count();
        long countChecks = checkWithProducts.stream()
                .filter(x -> x.getDateTime().getMonth() == LocalDateTime.now().getMonth())
                .filter(x -> Collections.frequency(d, x.getCheckId()) == 1).count();
        double totalA = checkWithProducts.stream().filter(x -> x.getDateTime().getMonth()
                == LocalDateTime.now().getMonth()).mapToDouble(CheckWithProducts::getPrice).sum();
        double totalB = 0.0;
        double totalC = 0.0;
        double totalSum = totalA + totalB + totalC;
        double ndsA = totalSum * 0.2;
        double ndsB = 0.0;
        double ndsC = 0.0;
        double totalNds = ndsA + ndsB + ndsC;
        return new Report(
                new Timestamp(System.currentTimeMillis()),
                countChecks,
                deletedCheckCount,
                totalA,
                totalB,
                totalC,
                totalSum,
                ndsA,
                ndsB,
                ndsC,
                totalNds
        );
    }

}
