package services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import model.Balance;
import repositories.BalancesRepository; 

public class BalanceService implements IBalanceService {

    private final BalancesRepository balanceRepository;

    public BalanceService() {
        this.balanceRepository = new BalancesRepository(); 
    }

    @Override
    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Optional<Balance> findById(String id) {
        if (id == null) return Optional.empty();
        String trimmed = id.trim();
        int idx = trimmed.indexOf('-'); // separar en la primera ocurrencia
        if (idx <= 0) return Optional.empty();

        String accountNumber = trimmed.substring(0, idx).trim().toUpperCase();
        String datePart = trimmed.substring(idx + 1).trim();

        // intentar parse ISO y dd-MM-yyyy
        DateTimeParseException lastEx = null;
        try {
            LocalDate date = LocalDate.parse(datePart);
            return balanceRepository.findByIds(accountNumber, date);
        } catch (DateTimeParseException e1) {
            lastEx = e1;
            try {
                java.time.format.DateTimeFormatter f = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date2 = LocalDate.parse(datePart, f);
                return balanceRepository.findByIds(accountNumber, date2);
            } catch (DateTimeParseException e2) {
                lastEx = e2;
            }
        }

        // fallback: buscar comparando la parte de fecha con toString() / dd-MM-yyyy en todos los registros
        for (Balance b : balanceRepository.findAll()) {
            if (b.getAccountNumber() == null) continue;
            if (!accountNumber.equalsIgnoreCase(b.getAccountNumber().trim())) continue;
            String iso = b.getDate().toString(); // yyyy-MM-dd
            String ddmmyyyy = b.getDate().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (datePart.equals(iso) || datePart.equals(ddmmyyyy)) {
                return Optional.of(b);
            }
        }

        // no se encontró
        return Optional.empty();
    }

    @Override
    public List<Balance> findAll() {
        return balanceRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        if (id == null) return false;
        String trimmed = id.trim();
        int idx = trimmed.indexOf('-');
        if (idx <= 0) return false;

        String accountNumber = trimmed.substring(0, idx).trim().toUpperCase();
        String datePart = trimmed.substring(idx + 1).trim();

        try {
            LocalDate date = LocalDate.parse(datePart);
            return balanceRepository.deleteByIds(accountNumber, date);
        } catch (DateTimeParseException e1) {
            try {
                java.time.format.DateTimeFormatter f = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date2 = LocalDate.parse(datePart, f);
                return balanceRepository.deleteByIds(accountNumber, date2);
            } catch (DateTimeParseException e2) {
                // fallback: eliminar comparando strings de fecha
                List<Balance> all = balanceRepository.findAll();
                for (Balance b : all) {
                    if (b.getAccountNumber() == null) continue;
                    if (!accountNumber.equalsIgnoreCase(b.getAccountNumber().trim())) continue;
                    String iso = b.getDate().toString();
                    String ddmmyyyy = b.getDate().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    if (datePart.equals(iso) || datePart.equals(ddmmyyyy)) {
                        return balanceRepository.deleteByIds(b.getAccountNumber(), b.getDate());
                    }
                }
                return false;
            }
        }
    }
}