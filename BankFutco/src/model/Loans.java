package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loans {
	// Atributos
	private LocalDate date;
	private String type; // e.g. "Home", "Vehicle", "Personal"
	private BigDecimal totalLoan;
	private BigDecimal amountPaid;
	private BigDecimal outstandingAmt;
}
