package com.transport.smart;

import com.transport.smart.model.*;
import com.transport.smart.repository.RouteRepository;
import com.transport.smart.repository.UserRepository;
import com.transport.smart.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class SmartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PasswordEncoder encoder;

    private static final Logger logger = LoggerFactory.getLogger(SmartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SmartApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("john@example.com");
        user.setPassword(encoder.encode("123456789"));
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
        logger.info(user.toString());
        userRepository.save(user);

        LocalDateTime now = LocalDateTime.now();
        Subscription sub = new Subscription();
        sub.setCreatedOn(now);
        sub.setExpiresOn(now.plusDays(7));
        User user2 = new User();
        user2.setEmail("bob@example.com");
        user2.setPassword(encoder.encode("abcdefghij"));
        user2.setFirstName("Bob");
        user2.setLastName("Ross");
        user2.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
        user2.setSubscription(sub);
        logger.info(user2.toString());
        userRepository.save(user2);
        user2.getSubscription().setExpiresOn(LocalDateTime.now().plusYears(10));
        userRepository.save(user2);


        Subscription sub2 = new Subscription();
        sub2.setCreatedOn(now);
        sub2.setExpiresOn(now.plusYears(6));
        user2 = userRepository.findByEmail("bob@example.com");
        user2.setSubscription(sub2);
        userRepository.save(user2);

        String[] stations = {"Rebreanu", "Brancoveanu", "Drubeta", "Alunis"};
        Route route = new Route();
        route.setRouteName("7");
        route.setStations(stations);
        routeRepository.save(route);

        String[] stations2 = {"Aradului", "Torontalului", "Martirilor", "Cetatii"};
        Route route2 = new Route();
        route2.setRouteName("E98");
        route2.setStations(stations2);
        routeRepository.save(route2);

        RaspberryPi raspberryPi = new RaspberryPi();
        raspberryPi.setIpAddress("192.168.0.100");
        raspberryPi.setPort("3000");

        Vehicle veh = new Vehicle();
        veh.setName("Bus One");
        veh.setPi(raspberryPi);
        veh.setRoute(route);

        vehicleRepository.save(veh);

    }
}
