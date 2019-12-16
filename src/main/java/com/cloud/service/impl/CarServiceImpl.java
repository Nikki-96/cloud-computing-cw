package com.cloud.service.impl;

import com.cloud.dao.CarDao;
import com.cloud.dao.OptionDao;
import com.cloud.model.Car;
import com.cloud.model.Option;
import com.cloud.service.CarService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author nsel0716 on 12/15/2019
 * @project cloud-computing-cw
 */
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    private final CarDao carDao;
    private final OptionDao optionDao;

    @Override
    public List<Car> getCarsByModelName(String modelName) {
        return carDao.findByModel(modelName);
    }

    @Override
    public List<Option> getOptionByCarID(Long carId) {
        return optionDao.findByCarId(carId);
    }

    @Override
    public Car getPriceByCarID(Long carId) {
        Optional<Car> optionalCar = carDao.findById(carId);
        return optionalCar.get();
    }
}
