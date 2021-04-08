package com.sigo.gestao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity(name = "productionResume")
public class DashboardData {

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
    private String title;
    private List<DashboardItem> items;
}