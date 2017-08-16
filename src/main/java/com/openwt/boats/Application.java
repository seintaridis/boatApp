package com.openwt.boats;

import com.openwt.boats.dao.BoatRepository;
import com.openwt.boats.dao.UserRepository;
import com.openwt.boats.entity.Boat;
import com.openwt.boats.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository repository, BoatRepository boatRepository) {
		return args -> {
            repository.save(new User("Dimitrios","Seintaridis","seinta","123"));

            boatRepository.save(new Boat("Poseidon","Very big boat",2999f,new Timestamp(System.currentTimeMillis()),"Seintaridis"));

			boatRepository.save(new Boat("Zeus","like thunder",300.f,new Timestamp(System.currentTimeMillis()),"Seintaridis"));


		};
	}

}
