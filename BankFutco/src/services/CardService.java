package services;

import java.util.List;
import java.util.Optional;
import model.Cards;
import repositories.CardRepository; // Asegúrate de que este import sea correcto

public class CardService implements ICardsService {

    // Se declara como final para asegurar que se inicialice una sola vez
    private final CardRepository cardRepository;
    
    // Constructor: Inicializa el repositorio
    public CardService() {
        this.cardRepository = new CardRepository();
    }
    
    @Override
    public Cards save(Cards card) {
        // Delega la lógica de guardar/actualizar al repositorio
        return cardRepository.save(card);
    }

    @Override
    public Optional<Cards> findById(String cardNumber) {
        // Delega la búsqueda al repositorio
        return cardRepository.findById(cardNumber);
    }

    @Override
    public List<Cards> findAll() {
        // Delega la lista completa al repositorio
        return cardRepository.findAll();
    }

    @Override
    public boolean deleteById(String cardNumber) {
        // Delega la eliminación al repositorio
        return cardRepository.deleteById(cardNumber);
    }
}