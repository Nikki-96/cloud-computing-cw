package com.cloud.dao;

import com.cloud.model.Car;
import com.cloud.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author nsel0716 on 12/15/2019
 * @project cloud-computing-cw
 */
public interface OptionDao extends JpaRepository<Option, Long> {
        List<Option> findByCarId(Long carID);
}
