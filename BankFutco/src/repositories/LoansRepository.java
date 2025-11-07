package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Loans;

public class LoansRepository {
    private final List<Loans> storageLoans = new ArrayList<>();

    public LoansRepository() {
        initData();
    }

    private void initData() {
        // Crear préstamos de ejemplo y asignar loanId para poder identificarlos
        Loans l1 = new Loans(
            "LN001",
            LocalDate.of(2025, 10, 5),
            "Home",
            new BigDecimal("50000000"),
            new BigDecimal("20000000"),
            new BigDecimal("30000000")
        );
        storageLoans.add(l1);

        // Agregando más préstamos de ejemplo con diferentes tipos y montos
        Loans l2 = new Loans(
            "LN002",
            LocalDate.now(),
            "Vehicle",
            new BigDecimal("25000000"),
            new BigDecimal("5000000"),
            new BigDecimal("20000000")
        );
        storageLoans.add(l2);

        Loans l3 = new Loans(
            "LN003",
            LocalDate.now(),
            "Personal",
            new BigDecimal("10000000"),
            new BigDecimal("2000000"),
            new BigDecimal("8000000")
        );
        storageLoans.add(l3);
    }

    // CRUD methods (similar to AccountRepository)
    public Loans save(Loans loan) {
        if (loan == null || loan.getLoanId() == null) {
            throw new IllegalArgumentException("Loan o loanId no puede ser null");
        }
        storageLoans.removeIf(l -> l.getLoanId().equals(loan.getLoanId()));
        storageLoans.add(loan);
        return loan;
    }

    public Optional<Loans> findById(String loanId) {
        if (loanId == null) return Optional.empty();
        return storageLoans.stream().filter(l -> loanId.equals(l.getLoanId())).findFirst();
    }

    public List<Loans> findAll() {
        return new ArrayList<>(storageLoans);
    }

    public boolean deleteById(String loanId) {
        return findById(loanId).map(l -> storageLoans.remove(l)).orElse(false);
    }

    public boolean existsById(String loanId) {
        return storageLoans.stream().anyMatch(l -> loanId != null && loanId.equals(l.getLoanId()));
    }

    public LocalDate fecha() {
        LocalDate fechaActual = LocalDate.now();
        return fechaActual;
    }
}

