package model;

import java.math.BigDecimal;

public class Cards {
	// Atributos
	private String cardNumber;
	private String type; // e.g. "Credit", "Debit"
	private BigDecimal totalLimit;
	private BigDecimal amountUsed;
	private BigDecimal available;
}
