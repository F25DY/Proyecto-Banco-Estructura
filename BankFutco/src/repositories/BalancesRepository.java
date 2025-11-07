package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Balance;

public class BalancesRepository {
    
    private final List<Balance> storage = new ArrayList<>();

    public BalancesRepository() {
        initData();
    }

    private void initData() {
        
        
        storage.add(new Balance("ACC001", LocalDate.of(2024, 7, 9), "Initial Deposit", new BigDecimal("1000.00"), BigDecimal.ZERO, new BigDecimal("1000.00")));
        storage.add(new Balance("ACC001", LocalDate.of(2024, 7, 10), "Purchase", BigDecimal.ZERO, new BigDecimal("50.00"), new BigDecimal("950.00")));
        storage.add(new Balance("ACC002", LocalDate.of(2024, 7, 9), "Initial Deposit", new BigDecimal("5000.00"), BigDecimal.ZERO, new BigDecimal("5000.00")));
        
        
        storage.add(new Balance("ACC020", LocalDate.of(2025, 3, 15), "ARRIENDO", new BigDecimal("150000"), new BigDecimal("12500"), new BigDecimal("250001")));
    }

    public Balance save(Balance balance) {
        if (balance == null || balance.getAccountNumber() == null || balance.getDate() == null) {
            throw new IllegalArgumentException("Balance, accountNumber o Date no pueden ser null");
        }
        
        storage.removeIf(b -> 
            b.getAccountNumber().equals(balance.getAccountNumber()) && 
            b.getDate().isEqual(balance.getDate()) 
        );
        storage.add(balance); 
        return balance;
    }

    
    public Optional<Balance> findByIds(String accountNumber, LocalDate date) {
        if (accountNumber == null || date == null) return Optional.empty();
        return storage.stream()
            .filter(b -> b.getAccountNumber() != null
                      && accountNumber.trim().equalsIgnoreCase(b.getAccountNumber().trim())
                      && date.isEqual(b.getDate()))
            .findFirst();
    }

    public List<Balance> findAll() {
        return new ArrayList<>(storage);
    }

    
    public boolean deleteByIds(String accountNumber, LocalDate date) {
        if (accountNumber == null || date == null) return false;
        return storage.removeIf(b -> accountNumber.equals(b.getAccountNumber()) && date.isEqual(b.getDate()));
    }
}