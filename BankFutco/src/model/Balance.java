package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Balance {
// Atributos
	private LocalDate date;
	private String description;
	private BigDecimal cashIn;
	private BigDecimal cashOut;
	private BigDecimal closingBalance;

// Constructor

     public Balance() {
	}
	
    public Balance(LocalDate date, String description, BigDecimal cashIn, BigDecimal cashOut,
			BigDecimal closingBalance) {
		this.date = date;
		this.description = description;
		this.cashIn = cashIn;
		this.cashOut = cashOut;
		this.closingBalance = closingBalance;
	}

// Getters y Setters

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getCashIn() {
		return cashIn;
	}
	public void setCashIn(BigDecimal cashIn) {
		this.cashIn = cashIn;
	}

	@Override
	public String toString() {
		return "Balance [date=" + date + ", description=" + description + ", cashIn=" + cashIn + ", cashOut=" + cashOut
				+ ", closingBalance=" + closingBalance + "]";
	}

}
