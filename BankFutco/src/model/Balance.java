package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Balance {
    
    private String accountNumber; 
	private LocalDate date;
	private String description;
	private BigDecimal cashIn;
	private BigDecimal cashOut;
	private BigDecimal closingBalance;

    public Balance() {
    }

    // CONSTRUCTOR CORREGIDO
    public Balance(String accountNumber, LocalDate date, String description, BigDecimal cashIn, BigDecimal cashOut, BigDecimal closingBalance) {
        this.accountNumber = accountNumber;
        this.date = date;
        this.description = description;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
        this.closingBalance = closingBalance;
    }

    // Getters y Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
	public LocalDate getDate() { return date; }
	public void setDate(LocalDate date) { this.date = date; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public BigDecimal getCashIn() { return cashIn; }
	public void setCashIn(BigDecimal cashIn) { this.cashIn = cashIn; }
	public BigDecimal getCashOut() { return cashOut; }
	public void setCashOut(BigDecimal cashOut) { this.cashOut = cashOut; }
	public BigDecimal getClosingBalance() { return closingBalance; }
	public void setClosingBalance(BigDecimal closingBalance) { this.closingBalance = closingBalance; }

    @Override
    public String toString() {
        return "Balance [accountNumber=" + accountNumber + ", date=" + date + ", description=" + description + ", cashIn=" + cashIn + ", cashOut=" + cashOut + ", closingBalance=" + closingBalance + "]";
    }
}