package com.cloud.component;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;

/**
 * @author nsel0716 on 12/19/2019
 * @project cloud-computing-cw
 */
@Component
@RequiredArgsConstructor
public class DemoData implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DemoData.class);

    private final EntityManager entityManager;

    Query query;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        query = entityManager.createNativeQuery("SELECT COUNT(*) FROM car");
        BigInteger result = (BigInteger) query.getSingleResult();
        if (result.intValue() <= 1) {
            query = entityManager.createNativeQuery("INSERT INTO car (name, model, price) VALUES ('2020 Acura MDX', 'Acura', 44400), ('2020 Acura ILX', 'Acura', 31500), " +
                    "('2019 Alfa Romeo 4C', 'Alfa Romeo', 70000), ('2019 Audi A5', 'Audi', 55000), ('2019 Audi S4', 'Audi', 52000), ('2019 BMW4 Series', 'BMW4', 103100);");
            query.executeUpdate();

            query = entityManager.createNativeQuery("INSERT INTO car_option (option_desc, car_id) " +
                    "VALUES ('Electronic safety', 1), ('Electronic safety', 2), ('Electronic safety', 3), ('Electronic safety', 4), " +
                    "('Keyless start', 5), ('Keyless start', 6), ('Keyless start', 2), ('Keyless start', 3), " +
                    "('Massaging Seats', 1), ('Massaging Seats', 2), " +
                    "('Night Vision', 5), ('Night Vision', 6), ('Night Vision', 1), " +
                    "('Air Scarf', 2), \n" +
                    "('High Tech Mouse', 6), ('High Tech Mouse', 5), ('High Tech Mouse', 4)");
            query.executeUpdate();
        }
    }
}