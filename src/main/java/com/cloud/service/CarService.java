package com.cloud.service;

import com.cloud.model.Car;
import com.cloud.model.Option;

import java.util.List;

/**
 * @author nsel0716 on 12/15/2019
 * @project cloud-computing-cw
 */
public interface CarService {

    List<Car> getCarsByModelName(String modelName);

    List<Option> getOptionByCarID(Long CarID);

    Car getPriceByCarID(Long CarID);
}
