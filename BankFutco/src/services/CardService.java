package services;

import java.util.List;
import java.util.Optional;
import model.Cards;
import repositories.CardRepository; 

public class CardService implements ICardsService {

    
    private final CardRepository cardRepository;
    
    
    public CardService() {
        this.cardRepository = new CardRepository();
    }
    
    @Override
    public Cards save(Cards card) {
        
        return cardRepository.save(card);
    }

    @Override
    public Optional<Cards> findById(String cardNumber) {
        
        return cardRepository.findById(cardNumber);
    }

    @Override
    public List<Cards> findAll() {
        
        return cardRepository.findAll();
    }

    @Override
    public boolean deleteById(String cardNumber) {
        
        return cardRepository.deleteById(cardNumber);
    }
}