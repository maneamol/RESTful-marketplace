package com.sjsu.market.beans;

public class PaymentInfo {
	int userId;
	long creditCardNumber;
	int csvNumber;
	float totalPayment;
	String time;

	public PaymentInfo(int userId, float totalPayment) {
		this.userId = userId;
		this.totalPayment = totalPayment;
	}

	public PaymentInfo(int userId, long creditCardNumber, int ccvNumber, float totalPayment) {
		this.userId = userId;
		this.creditCardNumber = creditCardNumber;
		this.csvNumber = ccvNumber;
		this.totalPayment = totalPayment;
	}

	public PaymentInfo(int userId, long creditCardNumber, int ccvNumber, float totalPayment, String time) {
		this.userId = userId;
		this.creditCardNumber = creditCardNumber;
		this.csvNumber = ccvNumber;
		this.totalPayment = totalPayment;
		this.time = time;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public float getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(float totalPayment) {
		this.totalPayment = totalPayment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCsvNumber() {
		return csvNumber;
	}

	public void setCsvNumber(int csvNumber) {
		this.csvNumber = csvNumber;
	}
}
