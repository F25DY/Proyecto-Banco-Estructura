package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Cards;

public class CardRepository {

    private List<Cards> storage = new ArrayList<>();

    // Constructor con datos de ejemplo
    public CardRepository() {
        storage.add(new Cards("CARD001", "Ana Perez", "1234-5678-9012-3456", "Visa", 5000.00));
        storage.add(new Cards("CARD002", "Luis Gomez", "2345-6789-0123-4567", "MasterCard", 3000.00));
        storage.add(new Cards("CARD003", "María Ruiz", "3456-7890-1234-5678", "Visa", 7000.00));
        storage.add(new Cards("CARD004", "Carlos Díaz", "4567-8901-2345-6789", "American Express", 4000.00));
        storage.add(new Cards("CARD005", "Sofia Morales", "5678-9012-3456-7890", "Visa", 6000.00));
        storage.add(new Cards("CARD006", "Diego Torres", "6789-0123-4567-8901", "MasterCard", 2000.00));
        storage.add(new Cards("CARD007", "Lucia Herrera", "7890-1234-5678-9012", "Visa", 8000.00));
        storage.add(new Cards("CARD008", "Miguel Ramos", "8901-2345-6789-0123", "MasterCard", 5500.00));
        storage.add(new Cards("CARD009", "Natalia Vega", "9012-3456-7890-1234", "Visa", 10000.00));
        storage.add(new Cards("CARD010", "Jorge Castro", "0123-4567-8901-2345", "American Express", 2500.00));
    }

    public List<Cards> getStorage() {
        return storage;
    }

    public void setStorage(List<Cards> storage) {
        this.storage = storage;
    }

    // Guardar una tarjeta
    public Cards save(Cards card) {
        storage.add(card);
        return card;
    }

    // Buscar una tarjeta por número
    public Optional<Cards> findById(String cardNumber) {
        return storage.stream()
                .filter(c -> c.getCardNumber().equals(cardNumber))
                .findFirst();
    }

    // Listar todas las tarjetas
    public List<Cards> findAll() {
        return new ArrayList<>(storage);
    }

    // Eliminar una tarjeta por número
    public boolean deleteById(String cardNumber) {
        return storage.removeIf(c -> c.getCardNumber().equals(cardNumber));
    }
}

