package com.ams.fare.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ams.fare.entity.Fare;
import com.ams.fare.service.FareService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fare")
public class FareController
{
	private final Logger      logger = LoggerFactory.getLogger(FareController.class);
	private final FareService fareService;

	@GetMapping
	public ResponseEntity<Fare> getFare(@RequestParam(value = "flightNumber") String flightNumber, @RequestParam(value = "flightDate") @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate)
	{
		logger
			.info("Searching fare with flightNumber: {} and flightDate: {}", flightNumber, flightDate);

		Fare fare = this.fareService.getFare(flightNumber, flightDate);
		return fare != null ? ResponseEntity.ok(fare)
				: ResponseEntity.notFound()
					.build();
	}
}
