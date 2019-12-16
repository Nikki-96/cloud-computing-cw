package com.cloud.dao;

import com.cloud.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author nsel0716 on 12/15/2019
 * @project cloud-computing-cw
 */
public interface CarDao extends JpaRepository<Car, Long> {
        List<Car> findByModel(String model);
}
