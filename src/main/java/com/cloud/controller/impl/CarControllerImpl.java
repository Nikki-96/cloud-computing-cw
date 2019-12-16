package com.cloud.controller.impl;

import com.cloud.controller.CarController;
import com.cloud.model.Car;
import com.cloud.model.Option;
import com.cloud.service.CarService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = {"car"}, description = "the car API")
public class CarControllerImpl implements CarController {

    private static final Logger log = LoggerFactory.getLogger(CarControllerImpl.class);
    private final CarService carService;

    public ResponseEntity<List<Car>> getCarsByModelName(@ApiParam(value = "Name of model of car to return",required=true) @PathVariable("modelName") String modelName) {
        List<Car> carList = carService.getCarsByModelName(modelName);
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    public ResponseEntity<List<Option>> getOptionByCarID(@ApiParam(value = "ID of car price which needs to be return",required=true) @PathVariable("carID") Long carID) {
        List<Option> optionList = carService.getOptionByCarID(carID);
        return new ResponseEntity<>(optionList, HttpStatus.OK);
    }

    public ResponseEntity<Car> getPriceByCarID(@ApiParam(value = "ID of car price which needs to be return",required=true) @PathVariable("carID") Long carID) {
        Car car = carService.getPriceByCarID(carID);
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

}
