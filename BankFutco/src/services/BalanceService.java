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
        
        // El ID debe venir como "Cuenta-Fecha" (ej: ACC001-2024-07-11)
        String trimmedId = id.trim();
        String[] parts = trimmedId.split("-");

        // Error 1: No busca por ID si no tiene el formato compuesto
        if (parts.length != 2) {
            // Se devuelve vacío si no tiene el formato esperado
            return Optional.empty(); 
        }
        
        try {
            String accountNumber = parts[0].trim();
            // Error 2: La fecha se parsea incorrectamente
            // Se usa trim() para asegurar que no hay espacios inesperados
            LocalDate date = LocalDate.parse(parts[1].trim()); 
            
            return balanceRepository.findByIds(accountNumber, date);
            
        } catch (DateTimeParseException e) {
            // El formato YYYY-MM-DD es incorrecto
            return Optional.empty();
        }
    }

    @Override
    public List<Balance> findAll() {
        return balanceRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        if (id == null) return false;
        
        // El ID debe venir como "Cuenta-Fecha"
        String trimmedId = id.trim();
        String[] parts = trimmedId.split("-");

        if (parts.length != 2) {
            return false;
        }
        
        try {
            String accountNumber = parts[0].trim();
            LocalDate date = LocalDate.parse(parts[1].trim()); 
            
            return balanceRepository.deleteByIds(accountNumber, date);
            
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}