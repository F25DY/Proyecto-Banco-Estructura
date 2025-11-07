package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loans {

    private String loanId; 
    private LocalDate date;
    private String type; 
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmt;

    public Loans (){
    }

   
    public Loans(String loanId, LocalDate date, String type, BigDecimal totalLoan, BigDecimal amountPaid, BigDecimal outstandingAmt) {
        this.loanId = loanId;
        this.date = date;
        this.type = type;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmt = outstandingAmt;
    }

    
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