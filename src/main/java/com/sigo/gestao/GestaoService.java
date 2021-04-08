package com.sigo.gestao;

import com.sigo.gestao.dto.StatusReportDto;
import com.sigo.gestao.model.DashboardData;
import com.sigo.gestao.model.DashboardItem;
import com.sigo.gestao.model.StatusReport;
import com.sigo.gestao.repository.StatusReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class GestaoService {

    @Autowired
    private StatusReportRepository statusRepo;

    @Autowired
    private SendGridMailService mailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GestaoController.class);

    // retorna os dados do dashboard de gestão
    public HashMap<String, ArrayList<DashboardData>> getDashboardData() {
        HashMap response = new HashMap<String, ArrayList<DashboardData>>();

        // produção diária
        String lastStatus = getLastStatusReport();
        DashboardItem status = new DashboardItem("status","Status", lastStatus);
        DashboardItem produced_items = new DashboardItem("produced_items","Tecidos fabricados/separados","30");
        DashboardItem stock_level = new DashboardItem("stock_level","Nível do estoque","45%");

        ArrayList<DashboardItem> production_items = new ArrayList<>();
        production_items.add(status);
        production_items.add(produced_items);
        production_items.add(stock_level);

        DashboardData production_day = new DashboardData();
        production_day.setTitle("Produção diária");
        production_day.setItems(production_items);

        // outros
        DashboardItem active_consultancies = new DashboardItem("active_consultancies","Consultorias/Acessorias","5 Ativos");
        ArrayList<DashboardItem> other_items = new ArrayList<>();
        other_items.add(active_consultancies);

        DashboardData others = new DashboardData();
        others.setTitle("Outros");
        others.setItems(other_items);

        ArrayList<DashboardData> data = new ArrayList<>();
        data.add(production_day);
        data.add(others);

        response.put("data", data);
        return response;
    }

    // reculpera o último status da produção diária
    private String getLastStatusReport() {
        Date date = new Date();
        Optional<StatusReport> statusReport = statusRepo.findTopByCreatedAtOrderByIdDesc(date);
        StatusReport status;

        if(statusReport.isEmpty()) {
            status = new StatusReport();
            status.setStatus("Ativo");
        } else {
            status = statusReport.get();
        }
        return status.getStatus();
    }

    // salvar um status da produção diária
    public void saveStatusReport(StatusReportDto statusReport) {
        StatusReport toSave = new StatusReport();
        toSave.setStatus(statusReport.getStatus());
        toSave.setDescription(statusReport.getDescription());
        toSave.setCreatedAt(new Date());

        try {
            StringBuilder message = new StringBuilder();
            message.append("Status da produção diária: ").append(statusReport.getStatus().toUpperCase())
                    .append(" / ").append(statusReport.getDescription());
            mailService.sendMail("glauberdf.ti@gmail.com", message.toString());
        } catch (IOException e) {
            LOGGER.error("Falha ao enviar email");
        }

        statusRepo.save(toSave);
    }
}
