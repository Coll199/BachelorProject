package com.transport.smart.repository;

import com.transport.smart.model.RaspberryPi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaspberryPiRepository extends JpaRepository<RaspberryPi, Long> {
}
