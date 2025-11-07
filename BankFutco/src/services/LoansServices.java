package services;

import java.util.List;
import java.util.Optional;

import model.Loans;
import repositories.LoansRepository; // Debe existir en el paquete repositories

public class LoansServices implements ILoansService {

    private final LoansRepository loansRepository;
    
    public LoansServices() {
        this.loansRepository = new LoansRepository();
    }
    
    @Override
    public Loans save(Loans loan) {
        return loansRepository.save(loan);
    }

    @Override
    // Usamos el 'id' (que será el loanId) para la búsqueda
    public Optional<Loans> findById(String id) { 
        return loansRepository.findById(id);
    }

    @Override
    public List<Loans> findAll() {
        return loansRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        return loansRepository.deleteById(id);
    }
}
