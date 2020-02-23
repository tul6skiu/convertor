package com.test.converteruploader.repository;

import com.test.converteruploader.model.entity.History;
import org.HdrHistogram.Histogram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUserName(String userName);
}
