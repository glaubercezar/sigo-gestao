package com.sigo.gestao.repository;

import com.sigo.gestao.model.StatusReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface StatusReportRepository extends JpaRepository<StatusReport, Long> {

    Optional<StatusReport> findTopByCreatedAtOrderByIdDesc(Date date);
}
