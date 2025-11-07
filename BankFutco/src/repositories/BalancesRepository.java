package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Balance;

// Nota: Aunque no se muestre aquí, esta clase implementaría idealmente la interfaz IBalanceRepository.
public class BalancesRepository {

    // 1. Almacenamiento en memoria para simular la base de datos (igual que AccountRepository)
    private final List<Balance> storage = new ArrayList<>();

    public BalancesRepository() {
		initData();
	}

    /**
     * Inicializa datos de prueba simulados.
     * Utiliza las cuentas ACC001 y ACC002 existentes en AccountRepository.
     * Asume que la clase 'Balance' tiene el campo 'accountNumber' y un constructor completo.
     */
	private void initData() {
        // Constructor: (accountNumber, date, description, cashIn, cashOut, closingBalance)
        
        // Saldos para ACC001 (Ana Perez)
        storage.add(new Balance("ACC001", LocalDate.now().minusDays(3), "Initial Deposit", new BigDecimal("1000.00"), BigDecimal.ZERO, new BigDecimal("1000.00")));
        storage.add(new Balance("ACC001", LocalDate.now().minusDays(1), "Transfer In", new BigDecimal("250.00"), BigDecimal.ZERO, new BigDecimal("1250.00")));
        storage.add(new Balance("ACC001", LocalDate.now(), "Purchase - Grocery", BigDecimal.ZERO, new BigDecimal("50.00"), new BigDecimal("1200.00")));
        
        // Saldos para ACC002 (Luis Gomez)
        storage.add(new Balance("ACC002", LocalDate.now().minusDays(5), "Opening Balance", new BigDecimal("5000.00"), BigDecimal.ZERO, new BigDecimal("5000.00")));
        storage.add(new Balance("ACC002", LocalDate.now().minusDays(2), "ATM Withdrawal", BigDecimal.ZERO, new BigDecimal("200.00"), new BigDecimal("4800.00")));
        storage.add(new Balance("ACC002", LocalDate.now(), "Salary Deposit", new BigDecimal("3500.00"), BigDecimal.ZERO, new BigDecimal("8300.00")));
	}
    
    // --- MÉTODOS CRUD ---

    public Balance save(Balance balance) {
        if (balance == null || balance.getAccountNumber() == null || balance.getDate() == null) {
			throw new IllegalArgumentException("Balance, accountNumber y date no pueden ser null");
		}
        
        // Simula la actualización: Elimina el registro anterior si la clave compuesta coincide
		storage.removeIf(b -> 
            b.getAccountNumber().equals(balance.getAccountNumber()) && 
            b.getDate().equals(balance.getDate())
        );
        storage.add(balance); // Agrega el registro (nuevo o actualizado)
        return balance;
	}

	public Optional<Balance> findByIds(String accountNumber, LocalDate date) {
		if (accountNumber == null || date == null) return Optional.empty();
        
        // Busca usando la clave compuesta (similar al findById de AccountRepository)
		return storage.stream()
            .filter(b -> accountNumber.equals(b.getAccountNumber()) && date.equals(b.getDate()))
            .findFirst();
	}
    
    public List<Balance> findByAccountNumber(String accountNumber) {
        if (accountNumber == null) return new ArrayList<>();
        // Filtra todos los balances que coincidan con el número de cuenta
        return storage.stream()
            .filter(b -> accountNumber.equals(b.getAccountNumber()))
            .toList(); 
    }

	public List<Balance> findAll() {
		return new ArrayList<>(storage);
	}

	public boolean deleteByIds(String accountNumber, LocalDate date) {
        // Busca el objeto y si existe (.map), lo elimina de la lista
        return findByIds(accountNumber, date)
            .map(storage::remove) 
            .orElse(false);
	}
    
	public boolean existsByIds(String accountNumber, LocalDate date) {
		return findByIds(accountNumber, date).isPresent();
	}
}