package br.com.droolsListener;

import java.io.Serializable;

public class Flight implements Serializable {
	static final long serialVersionUID = 1L;

	private String airline;
	private String departureDate;
	private Double fare;
	private Boolean international;
	private String origin;
	private String roundTrip;
	private String seatClass;

	private String arrival;

	private Double convertionRate;

	private Double ruleConvertionRate;

	public Flight() {
	}

	public String getAirline() {
		return this.airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getDepartureDate() {
		return this.departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public Double getFare() {
		return this.fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public Boolean getInternational() {
		return this.international;
	}

	public void setInternational(Boolean international) {
		this.international = international;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getRoundTrip() {
		return this.roundTrip;
	}

	public void setRoundTrip(String roundTrip) {
		this.roundTrip = roundTrip;
	}

	public String getSeatClass() {
		return this.seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public String getArrival() {
		return this.arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public void addConvertionRate() {
		this.addConvertionRate(getRuleConvertionRate());
	}

	public void addConvertionRate(Double value) {
		if (this.convertionRate == null)
			this.convertionRate = 0D;
		this.convertionRate += value;
	}

	public void multiplyConvertionRate(Double value) {
		if (this.convertionRate == null || this.convertionRate == 0D) {
			this.convertionRate = 0D;
		} else {
			this.convertionRate = this.convertionRate * value;
		}
	}

	public Double getConvertionRate() {
		return this.convertionRate;
	}

	public void setConvertionRate(Double convertionRate) {
		this.convertionRate = convertionRate;
	}

	public Double getRuleConvertionRate() {
		return this.ruleConvertionRate;
	}

	public void setRuleConvertionRate(Double ruleConvertionRate) {
		this.ruleConvertionRate = ruleConvertionRate;
	}

	public Flight(String airline, String departureDate, Double fare, Boolean international, String origin, String roundTrip, String seatClass, String arrival,
			Double convertionRate, Double ruleConvertionRate) {
		this.airline = airline;
		this.departureDate = departureDate;
		this.fare = fare;
		this.international = international;
		this.origin = origin;
		this.roundTrip = roundTrip;
		this.seatClass = seatClass;
		this.arrival = arrival;
		this.convertionRate = convertionRate;
		this.ruleConvertionRate = ruleConvertionRate;
	}
}
