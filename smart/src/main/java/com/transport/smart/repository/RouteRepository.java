package com.transport.smart.repository;

import com.transport.smart.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
    boolean existsById(Long id);
    boolean existsByRouteName(String routeName);
    Route findByRouteName(String routeName);
}
