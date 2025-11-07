package services;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeParseException; 

import model.Balance;
import repositories.BalancesRepository; // ¡Importante! Asegúrate que la clase se llame BalancesRepository

// La clase implementa el contrato definido en IBalanceService
public class BalanceService implements IBalanceService {

    // Instancia del repositorio (para acceder a los datos)
    private final BalancesRepository balanceRepository;

    public BalanceService() {
        this.balanceRepository = new BalancesRepository(); 
    }

    // --- MÉTODOS DELEGADOS AL REPOSITORIO ---

    @Override
    public Balance save(Balance balance) {
        // Delega al repositorio la lógica de guardar/actualizar
        return balanceRepository.save(balance);
    }

    @Override
    public Optional<Balance> findById(String id) {
        // SOLUCIÓN PARA LA CLAVE COMPUESTA: Descomponer el String (ej: "ACC001-2024-07-11")
        try {
            // Se usa split("-") para dividir el ID en número de cuenta y fecha
            String[] parts = id.split("-");
            if (parts.length != 2) {
                // Si no hay dos partes separadas por '-', el formato es incorrecto.
                System.err.println("Formato de ID inválido. Debe ser: Cuenta-Fecha (ej: ACC001-2024-07-11)");
                return Optional.empty(); 
            }
            
            String accountNumber = parts[0];
            LocalDate date = LocalDate.parse(parts[1]); 
            
            // Llama al método findByIds del repositorio
            return balanceRepository.findByIds(accountNumber, date);
            
        } catch (DateTimeParseException e) {
            // Captura el error si la fecha (parts[1]) no tiene el formato YYYY-MM-DD
            System.err.println("Error de formato de fecha en el ID: " + id);
            return Optional.empty();
        }
    }

    @Override
    public List<Balance> findAll() {
        // Delega la obtención de todos los registros al repositorio
        return balanceRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        // SOLUCIÓN PARA LA CLAVE COMPUESTA: Descomponer el String para eliminar el registro
        try {
            String[] parts = id.split("-");
            if (parts.length != 2) {
                System.err.println("Formato de ID inválido para eliminar. Debe ser: Cuenta-Fecha.");
                return false; 
            }
            
            String accountNumber = parts[0];
            LocalDate date = LocalDate.parse(parts[1]); 
            
            // Llama al método deleteByIds del repositorio
            return balanceRepository.deleteByIds(accountNumber, date);
            
        } catch (DateTimeParseException e) {
            System.err.println("Error de formato de fecha al intentar eliminar: " + id);
            return false;
        }
    }
}