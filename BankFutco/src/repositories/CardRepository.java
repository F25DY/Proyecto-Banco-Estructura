package repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 
import model.Cards;

public class CardRepository {

    private final List<Cards> storage = new ArrayList<>();

    
    public CardRepository() {
        initData();
    }

    
    private void initData() {
        
        
    
        storage.add(new Cards("CARD001", "Credit", new BigDecimal("5000.00"), new BigDecimal("1500.00"), new BigDecimal("3500.00")));
        storage.add(new Cards("CARD002", "Debit", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"))); 
        storage.add(new Cards("CARD003", "Credit", new BigDecimal("7000.00"), new BigDecimal("100.00"), new BigDecimal("6900.00")));
        storage.add(new Cards("CARD004", "Credit", new BigDecimal("4000.00"), new BigDecimal("3500.00"), new BigDecimal("500.00")));
        storage.add(new Cards("CARD005", "Debit", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")));
        storage.add(new Cards("CARD006", "Credit", new BigDecimal("2000.00"), new BigDecimal("0.00"), new BigDecimal("2000.00")));
        storage.add(new Cards("CARD007", "Credit", new BigDecimal("8000.00"), new BigDecimal("4000.00"), new BigDecimal("4000.00")));
    }

    
    public Cards save(Cards card) {
        if (card == null || card.getCardNumber() == null) {
            throw new IllegalArgumentException("Card o cardNumber no puede ser null");
        }
        
        storage.removeIf(c -> c.getCardNumber().equals(card.getCardNumber()));
        storage.add(card);
        return card;
    }

    
    public Optional<Cards> findById(String cardNumber) {
        if (cardNumber == null) return Optional.empty();
        return storage.stream()
                .filter(c -> cardNumber.equals(c.getCardNumber()))
                .findFirst();
    }

  
    public List<Cards> findAll() {
        return new ArrayList<>(storage);
    }

    
    public boolean deleteById(String cardNumber) {
        return storage.removeIf(c -> c.getCardNumber().equals(cardNumber));
    }
}