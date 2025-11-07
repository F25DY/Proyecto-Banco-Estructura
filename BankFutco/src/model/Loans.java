package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loans{

    // Atributos
    private String loanId; // Clave para la búsqueda
    private LocalDate date;
    private String type; // e.g. "Home", "Vehicle", "Personal"
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmt;

    // Constructor sin argumentos
    public Loans (){
    }

    // CONSTRUCTOR COMPLETO (¡CORREGIDO para incluir loanId!)
    public Loans(String loanId, LocalDate date, String type, BigDecimal totalLoan, BigDecimal amountPaid, BigDecimal outstandingAmt) {
        this.loanId = loanId;
        this.date = date;
        this.type = type;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmt = outstandingAmt;
    }


    // Getters y Setters
    
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public BigDecimal getTotalLoan() {
        return totalLoan;
    }
    
    public void setTotalLoan(BigDecimal totalLoan) {
        this.totalLoan = totalLoan;
    }
    
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }
    
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    public BigDecimal getOutstandingAmt() {
        return outstandingAmt;
    }
    
    public void setOutstandingAmt(BigDecimal outstandingAmt) {
        this.outstandingAmt = outstandingAmt;
    }

    @Override
    public String toString() {
        return "Loans [loanId=" + loanId + ", date=" + date + ", type=" + type + ", totalLoan=" + totalLoan + ", amountPaid=" + amountPaid
            + ", outstandingAmt=" + outstandingAmt + "]";
    }
}