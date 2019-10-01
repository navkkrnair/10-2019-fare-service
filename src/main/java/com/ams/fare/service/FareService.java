package com.ams.fare.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ams.fare.entity.Fare;
import com.ams.fare.repository.FareRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FareService
{
	private final Logger logger = LoggerFactory.getLogger(FareService.class);

	private final FareRepository fareRepository;

	public Fare getFare(String flightNumber, LocalDate flightDate)
	{
		logger.info("Looking for fares flightNumber " + flightNumber + " flightDate " + flightDate);
		return this.fareRepository.getFareByFlightNumberAndFlightDate(flightNumber,
				flightDate);
	}
}
