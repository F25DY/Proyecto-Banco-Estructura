import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import model.Account;
import model.Balance;
import model.Cards;
import model.Loans;
import services.AccountService;
import services.BalanceService;
import services.CardService;
import services.LoansServices;

// Si tu archivo App.java NO está en un paquete, COMENTA O BORRA la línea "package app;".

public class App {
    
    // Inicialización de TODOS los servicios
    private static final AccountService accountService = new AccountService();
    private static final BalanceService balanceService = new BalanceService();
    private static final LoansServices loansService = new LoansServices();
    private static final CardService cardService = new CardService();

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1":
                        runCrudMenu(sc, "Account");
                        break;
                    case "2":
                        runCrudMenu(sc, "Balance");
                        break;
                    case "3":
                        runCrudMenu(sc, "Loans");
                        break;
                    case "4":
                        runCrudMenu(sc, "Cards");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void runCrudMenu(Scanner sc, String entityName) {
        boolean back = false;
        while (!back) {
            printCrudMenu(entityName);
            String opt = sc.nextLine().trim();

            switch (opt) {
<<<<<<< HEAD
                case "1": // CREATE
                    System.out.println("[" + entityName + "] Crear - Ingrese datos:");
                    handleCreateOrUpdate(sc, entityName, "Create");
=======
                case "1":
                    
                    System.out.println("Ingrese los siguientes datos separados por espacios: número de la cuenta, nombre, email, celular, tipo de cuenta y dirección");
                    String entrada= sc.nextLine();
                    String [] partes= entrada.split(" ");

                    Account account= new Account(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);

                    accountService.save(account);
                    System.out.println("Cuenta creada");
                    
                    /*Account account = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001", "Savings", "Calle 20 de Turbaco-Bolivar"); 
                    accountService.save(account); */
>>>>>>> 34eca8ee3c5a2b5d1d1dc5479a48e339ac814eb6
                    break;
                case "2": // READ BY ID
                    System.out.print("[" + entityName + "] Leer por id - Ingrese ID: ");
                    String idRead = sc.nextLine().trim();
                    handleRead(entityName, idRead);
                    break;
                case "3": // LIST ALL
                    System.out.println("[" + entityName + "] Listar todos");
                    handleListAll(entityName);
                    break;
                case "4": // UPDATE
                    // ¡Recordatorio CRÍTICO para Balance!
                    if (entityName.equals("Balance")) {
                        System.out.print("[" + entityName + "] Actualizar - Ingrese ID compuesto (ej: ACC001-2024-07-11): ");
                    } else {
                        System.out.print("[" + entityName + "] Actualizar - Ingrese ID a actualizar: ");
                    }
                    String idUp = sc.nextLine().trim();
                    handleCreateOrUpdate(sc, entityName, "Update", idUp);
                    break;
                case "5": // DELETE
                    if (entityName.equals("Balance")) {
                        System.out.print("[" + entityName + "] Eliminar - Ingrese ID compuesto (ej: ACC001-2024-07-11): ");
                    } else {
                        System.out.print("[" + entityName + "] Eliminar - Ingrese ID a eliminar: ");
                    }
                    String idDel = sc.nextLine().trim();
                    handleDelete(entityName, idDel);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // --- MÉTODOS AUXILIARES CON ENTRADA DE USUARIO (Scanner) ---
    
    private static void handleCreateOrUpdate(Scanner sc, String entityName, String action, String... id) {
        String targetId = id.length > 0 ? id[0] : null; 
        
        try {
            switch (entityName) {
                case "Account":
                    String accId;
                    if (action.equals("Create")) {
                        System.out.print("Ingrese ID de Cuenta (ej: ACC011): ");
                        accId = sc.nextLine().trim();
                    } else {
                        accId = targetId;
                        if (accountService.findById(accId).isEmpty()) {
                            System.out.println("Error: Cuenta con ID " + accId + " no encontrada para actualizar.");
                            return;
                        }
                    }
                    System.out.print("Nombre del Cliente: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String phone = sc.nextLine();
                    System.out.print("Tipo (Savings/Checking): ");
                    String type = sc.nextLine();
                    System.out.print("Dirección: ");
                    String address = sc.nextLine();

                    Account account = new Account(accId, name, email, phone, type, address);
                    accountService.save(account);
                    System.out.println("✅ Cuenta " + accId + " guardada/actualizada con éxito.");
                    break;

                case "Balance":
                    String balAccId;
                    LocalDate balDate;
                    
                    if (action.equals("Create")) {
                        System.out.print("Ingrese ID de Cuenta asociada (ej: ACC001): ");
                        balAccId = sc.nextLine().trim();
                        System.out.print("Ingrese Fecha del registro (YYYY-MM-DD): ");
                        balDate = LocalDate.parse(sc.nextLine().trim());
                    } else { 
                        // VALIDACIÓN ROBUSTA DEL ID COMPUESTO PARA ACTUALIZAR
                        if (targetId == null) throw new IllegalArgumentException("ID compuesto requerido para actualizar Balance (ej: ACC001-2024-07-11).");
                        
                        String trimmedTargetId = targetId.trim(); // Limpiamos el ID del usuario
                        String[] parts = trimmedTargetId.split("-");
                        
                        if (parts.length != 2) throw new IllegalArgumentException("El formato del ID de Balance debe ser Cuenta-Fecha (ej: ACC001-2024-07-11).");
                        
                        balAccId = parts[0].trim();
                        balDate = LocalDate.parse(parts[1].trim()); // Limpiamos y parseamos la fecha del ID
                        
                        // Verificamos si el registro existe antes de pedir nuevos datos
                        if (balanceService.findById(trimmedTargetId).isEmpty()) {
                             System.out.println("Error: Balance con ID " + trimmedTargetId + " no encontrado para actualizar.");
                             return;
                        }
                    }

                    System.out.print("Descripción: ");
                    String description = sc.nextLine();
                    System.out.print("Entrada de Efectivo (Cash In, ej: 1000.00): ");
                    BigDecimal cashIn = new BigDecimal(sc.nextLine());
                    System.out.print("Salida de Efectivo (Cash Out, ej: 50.00): ");
                    BigDecimal cashOut = new BigDecimal(sc.nextLine());
                    System.out.print("Saldo Final (Closing Balance): ");
                    BigDecimal closingBalance = new BigDecimal(sc.nextLine());

                    Balance newBalance = new Balance(balAccId, balDate, description, cashIn, cashOut, closingBalance);
                    balanceService.save(newBalance);
                    System.out.println("✅ Balance para " + balAccId + " en " + balDate + " guardado/actualizado.");
                    break;
                
                case "Loans":
                    String loanId;
                    if (action.equals("Create")) {
                        System.out.print("Ingrese ID de Préstamo (ej: L005): ");
                        loanId = sc.nextLine().trim();
                    } else {
                        loanId = targetId;
                        if (loansService.findById(loanId).isEmpty()) {
                            System.out.println("Error: Préstamo con ID " + loanId + " no encontrado para actualizar.");
                            return;
                        }
                    }

                    System.out.print("Fecha de registro (YYYY-MM-DD): ");
                    LocalDate loanDate = LocalDate.parse(sc.nextLine().trim());
                    System.out.print("Tipo de Préstamo (Home/Vehicle/Personal): ");
                    String loanType = sc.nextLine();
                    System.out.print("Monto Total del Préstamo: ");
                    BigDecimal totalLoan = new BigDecimal(sc.nextLine());
                    System.out.print("Monto Pagado hasta la fecha: ");
                    BigDecimal amountPaid = new BigDecimal(sc.nextLine());
                    System.out.print("Monto Pendiente: ");
                    BigDecimal outstandingAmt = new BigDecimal(sc.nextLine());

                    Loans newLoan = new Loans(loanId, loanDate, loanType, totalLoan, amountPaid, outstandingAmt);
                    loansService.save(newLoan);
                    System.out.println("✅ Préstamo " + loanId + " guardado/actualizado.");
                    break;
                
                case "Cards":
                    String cardId;
                    if (action.equals("Create")) {
                        System.out.print("Ingrese ID de Tarjeta (ej: C008): ");
                        cardId = sc.nextLine().trim();
                    } else {
                        cardId = targetId;
                        if (cardService.findById(cardId).isEmpty()) {
                            System.out.println("Error: Tarjeta con ID " + cardId + " no encontrada para actualizar.");
                            return;
                        }
                    }

                    System.out.print("Tipo de Tarjeta (Credit/Debit): ");
                    String cardType = sc.nextLine();
                    System.out.print("Límite Total: ");
                    BigDecimal totalLimit = new BigDecimal(sc.nextLine());
                    System.out.print("Monto Usado: ");
                    BigDecimal amountUsed = new BigDecimal(sc.nextLine());
                    System.out.print("Monto Disponible: ");
                    BigDecimal available = new BigDecimal(sc.nextLine());

                    Cards newCard = new Cards(cardId, cardType, totalLimit, amountUsed, available);
                    cardService.save(newCard);
                    System.out.println("✅ Tarjeta " + cardId + " guardada/actualizada.");
                    break;
            }
        } catch (DateTimeParseException e) {
            // Este catch maneja las fechas que ingresa el usuario dentro del CRUD
            System.err.println("❌ Error: Formato de fecha inválido. Use YYYY-MM-DD (ej: 2024-07-11).");
            // Limpiar el buffer del scanner
            if (sc.hasNextLine()) {
                sc.nextLine(); 
            }
        } catch (NumberFormatException e) {
            System.err.println("❌ Error: Monto inválido. Use solo números (ej: 1000.00) para los campos monetarios.");
            // Limpiar el buffer del scanner
            if (sc.hasNextLine()) {
                sc.nextLine(); 
            }
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error en la lógica: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Ocurrió un error inesperado al procesar la solicitud: " + e.getMessage());
        }
    }

    private static void handleRead(String entityName, String id) {
        Optional<?> result = Optional.empty();
        
        // El ID siempre se limpia aquí antes de pasarlo al Service
        String cleanedId = id.trim();

        switch (entityName) {
            case "Account":
                result = accountService.findById(cleanedId);
                break;
            case "Balance":
                // BalanceService.findById() ahora valida y descompone el ID compuesto
                result = balanceService.findById(cleanedId); 
                break;
            case "Loans":
                result = loansService.findById(cleanedId);
                break;
            case "Cards":
                result = cardService.findById(cleanedId);
                break;
        }

        result.ifPresentOrElse(
            item -> System.out.println("Encontrado: " + item),
            () -> {
                if (entityName.equals("Balance") && !cleanedId.contains("-")) {
                    System.out.println("Error: Para Balance, el ID debe ser compuesto (Cuenta-Fecha, ej: ACC001-2024-07-11).");
                } else {
                    System.out.println(entityName + " con ID=" + cleanedId + " no encontrado.");
                }
            }
        );
    }

    private static void handleListAll(String entityName) {
        List<?> list = List.of();
        
        switch (entityName) {
            case "Account":
                list = accountService.findAll();
                break;
            case "Balance":
                list = balanceService.findAll();
                break;
            case "Loans":
                list = loansService.findAll();
                break;
            case "Cards":
                list = cardService.findAll();
                break;
        }

        if (list.isEmpty()) {
            System.out.println("No hay registros de " + entityName + ".");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void handleDelete(String entityName, String id) {
        boolean deleted = false;
        String cleanedId = id.trim();
        
        switch (entityName) {
            case "Account":
                deleted = accountService.deleteById(cleanedId);
                break;
            case "Balance":
                deleted = balanceService.deleteById(cleanedId);
                break;
            case "Loans":
                deleted = loansService.deleteById(cleanedId);
                break;
            case "Cards":
                deleted = cardService.deleteById(cleanedId);
                break;
        }

        if (deleted) {
            System.out.println("✅ " + entityName + " con ID=" + cleanedId + " eliminado correctamente.");
        } else {
            if (entityName.equals("Balance") && !cleanedId.contains("-")) {
                System.out.println("❌ Error: Para Balance, el ID debe ser compuesto (Cuenta-Fecha, ej: ACC001-2024-07-11).");
            } else {
                System.out.println("❌ Error: " + entityName + " con ID=" + cleanedId + " no encontrado o no se pudo eliminar.");
            }
        }
    }
    
    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }
}