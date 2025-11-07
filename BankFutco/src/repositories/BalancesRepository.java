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
        // DATOS DE PRUEBA INICIALES MÍNIMOS, usando LocalDate.of(YYYY, M, D) para MÁXIMA ROBUSTEZ
        
        storage.add(new Balance("ACC001", LocalDate.of(2024, 7, 9), "Initial Deposit", new BigDecimal("1000.00"), BigDecimal.ZERO, new BigDecimal("1000.00")));
        storage.add(new Balance("ACC001", LocalDate.of(2024, 7, 10), "Purchase", BigDecimal.ZERO, new BigDecimal("50.00"), new BigDecimal("950.00")));
        storage.add(new Balance("ACC002", LocalDate.of(2024, 7, 9), "Initial Deposit", new BigDecimal("5000.00"), BigDecimal.ZERO, new BigDecimal("5000.00")));
        
        // ¡AGREGAMOS TU DATO PROBLEMÁTICO USANDO EL FORMATO ROBUSTO DE LOCALDATE!
        storage.add(new Balance("ACC020", LocalDate.of(2025, 3, 15), "ARRIENDO", new BigDecimal("150000"), new BigDecimal("12500"), new BigDecimal("250001")));
    }

    public Balance save(Balance balance) {
        if (balance == null || balance.getAccountNumber() == null || balance.getDate() == null) {
            throw new IllegalArgumentException("Balance, accountNumber o Date no pueden ser null");
        }
        // Simula la actualización: Elimina el registro anterior si existe
        storage.removeIf(b -> 
            b.getAccountNumber().equals(balance.getAccountNumber()) && 
            b.getDate().isEqual(balance.getDate()) // Usamos isEqual para comparación de fechas más segura
        );
        storage.add(balance); // Agrega el registro (nuevo o actualizado)
        return balance;
    }

    // Método de búsqueda para la clave compuesta (Usado por BalanceService)
    public Optional<Balance> findByIds(String accountNumber, LocalDate date) {
        if (accountNumber == null || date == null) return Optional.empty();
        return storage.stream()
            .filter(b -> accountNumber.equals(b.getAccountNumber()) && date.isEqual(b.getDate())) // CRÍTICO: Usar isEqual para LocalDate
            .findFirst();
    }

    public List<Balance> findAll() {
        return new ArrayList<>(storage);
    }

    // Método de eliminación para la clave compuesta (Usado por BalanceService)
    public boolean deleteByIds(String accountNumber, LocalDate date) {
        if (accountNumber == null || date == null) return false;
        return storage.removeIf(b -> accountNumber.equals(b.getAccountNumber()) && date.isEqual(b.getDate()));
    }
}