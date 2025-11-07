package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Loans;

public class LoansRepository {

    private final List<Loans> storage = new ArrayList<>();

    public LoansRepository() {
        initData();
    }

    private void initData() {
        
        
        storage.add(new Loans("L001", LocalDate.of(2023, 1, 15), "Home", new BigDecimal("150000.00"), new BigDecimal("10000.00"), new BigDecimal("140000.00")));
        storage.add(new Loans("L002", LocalDate.of(2023, 5, 20), "Vehicle", new BigDecimal("35000.00"), new BigDecimal("5000.00"), new BigDecimal("30000.00")));
        storage.add(new Loans("L003", LocalDate.of(2024, 2, 1), "Personal", new BigDecimal("5000.00"), new BigDecimal("1000.00"), new BigDecimal("4000.00")));
        storage.add(new Loans("L004", LocalDate.of(2024, 7, 10), "Home", new BigDecimal("250000.00"), new BigDecimal("0.00"), new BigDecimal("250000.00")));
    }

    
    public Loans save(Loans loan) {
        if (loan == null || loan.getLoanId() == null) {
            throw new IllegalArgumentException("Loan o loanId no puede ser null");
        }
        
        storage.removeIf(l -> l.getLoanId().equals(loan.getLoanId()));
        storage.add(loan);
        return loan;
    }

    public Optional<Loans> findById(String loanId) {
        if (loanId == null) return Optional.empty();
        return storage.stream()
                .filter(l -> loanId.equals(l.getLoanId()))
                .findFirst();
    }

    public List<Loans> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String loanId) {
        return storage.removeIf(l -> l.getLoanId().equals(loanId));
    }
}