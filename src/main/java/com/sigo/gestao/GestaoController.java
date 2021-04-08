package com.sigo.gestao;

import com.sigo.gestao.dto.StatusReportDto;
import com.sigo.gestao.model.DashboardData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/api/v1/gestao", produces = MediaType.APPLICATION_JSON_VALUE)
public class GestaoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GestaoController.class);

    @Autowired
    private GestaoService gestaoService;

    @GetMapping("/dashboard")
    public ResponseEntity<HashMap<String, ArrayList<DashboardData>>> getDashboardData() {

        HashMap<String, ArrayList<DashboardData>> resume = gestaoService.getDashboardData();
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }

    @PostMapping("/status-report")
    public ResponseEntity<?> statusReport(@RequestBody StatusReportDto payload) {

        gestaoService.saveStatusReport(payload);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}