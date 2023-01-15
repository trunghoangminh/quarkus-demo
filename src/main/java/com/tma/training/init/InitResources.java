package com.tma.training.init;

import com.tma.training.product.entity.Product;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.event.Observes;
import java.math.BigDecimal;

/**
 * Init default resources
 */
@JBossLog
public class InitResources {
    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting...");
        if (Product.findAll().count() == 0) {
            Product laptop = new Product();
            laptop.setName("Macbook M1");
            laptop.setDescription("Laptop with Chip Apple Silicon");
            laptop.setManufacturer("Apple");
            laptop.setPrice(new BigDecimal(41_000_00));
            laptop.persist();

            Product ipad = new Product();
            ipad.setName("IPad M1");
            ipad.setDescription("IPad with Chip Apple Silicon");
            ipad.setManufacturer("Apple");
            ipad.setPrice(new BigDecimal(20_000_00));
            ipad.persist();

            Product iphone = new Product();
            iphone.setName("Iphone 14");
            iphone.setDescription("Support dynamic island");
            iphone.setManufacturer("Apple");
            iphone.setPrice(new BigDecimal(34_000_00));
            iphone.persist();
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("The application is stopping...");
    }
}

