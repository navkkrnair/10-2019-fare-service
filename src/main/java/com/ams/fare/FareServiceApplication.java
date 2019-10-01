package com.ams.fare;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.ams.fare.entity.Fare;
import com.ams.fare.repository.FareRepository;

import brave.sampler.Sampler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
@EnableDiscoveryClient
public class FareServiceApplication
{
	private static final Logger logger = LoggerFactory.getLogger(FareServiceApplication.class);

	private final FareRepository fareRepository;
	
	@Bean
	public Sampler defaultSampler()
	{
		return Sampler.ALWAYS_SAMPLE;
	}

	public static void main(String[] args)
	{
		SpringApplication.run(FareServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init()
	{
		return args ->
		{
			List<Fare> list = Arrays.asList(new Fare("BF100", LocalDate
				.of(2019, 01, 22), "100"), new Fare("BF101", LocalDate
					.of(2019, 01, 22), "101"), new Fare("BF102", LocalDate
						.of(2019, 01, 22), "102"), new Fare("BF103", LocalDate
							.of(2019, 01, 22), "103"), new Fare("BF104", LocalDate
								.of(2019, 01, 22), "104"), new Fare("BF105", LocalDate
									.of(2019, 01, 22), "105"), new Fare("BF106", LocalDate
										.of(2019, 01, 22), "106"));
			list.forEach(fare -> this.fareRepository.save(fare));

			logger.info("Result: " + this.fareRepository
				.getFareByFlightNumberAndFlightDate("BF101", LocalDate.of(2019, 01, 22)));

		};
	}

}
