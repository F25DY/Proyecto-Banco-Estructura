import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import model.Account;
import model.Balance;
import services.AccountService;
import services.BalanceService;

public class App {
    private static AccountService accountService=new AccountService();
    private static BalanceService balanceService = new BalanceService();
    public static void main(String[] args) throws Exception {
        ////accountService.findAll().stream().forEach(a-> System.out.println(a));
        //accountService.findAll().stream().forEach(System.out::println);
        //Account account = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001",
        //        "Savings", "Calle 20 de Turbaco-Bolivar");
        //accountService.save(account);
        //System.out.println("*".repeat(100));
        //accountService.findAll().stream().forEach(System.out::println);
        //System.out.println("*".repeat(100));
        ////accountService.findById("ACC009").ifPresentOrElse(
        ////        acc -> System.out.println("Encontrado: " + acc),
        ////        () -> System.out.println(" account number no encontrado."));
        //Optional<Account> a = accountService.findById("ACC009");
        //if(a==null){
        //    System.out.println(" account number no encontrado.");
        //}
        //else{
        //    System.out.println( "Encontrado: " + a);
        //}
        //accountService.deleteById("ACC010");
        //System.out.println("/".repeat(100));
        //accountService.findAll().stream().forEach(System.out::println);

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
                case "1":
                    if ("Account".equals(entityName)) {
                        System.out.println("Ingrese los siguientes datos separados por espacios: número de la cuenta, nombre, email, celular, tipo de cuenta y dirección");
                        String entrada = sc.nextLine();
                        String[] partes = entrada.split(" ");
                        if (partes.length != 6) {
                            System.out.println("Entrada inválida. Se requieren 6 valores separados por espacios.");
                        } else {
                            Account account = new Account(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
                            accountService.save(account);
                            System.out.println("Cuenta creada");
                        }
                    }

                    if ("Balance".equals(entityName)) {
                        System.out.println("Ingrese los siguientes datos separados por espacios: fecha(YYYY-MM-DD) número_de_cuenta descripción cashIn cashOut closingBalance");
                        String entrada = sc.nextLine();
                        String[] partes = entrada.split(" ");
                        if (partes.length != 6) {
                            System.out.println("Entrada inválida. Se requieren 6 valores separados por espacios.");
                        } else {
                            try {
                                String fechaStr = partes[0].trim();
                                String accountNumber = partes[1].trim();
                                String descripcion = partes[2].trim();
                                java.time.LocalDate fecha = LocalDate.parse(fechaStr);
                                java.math.BigDecimal cashIn = new BigDecimal(partes[3].trim());
                                java.math.BigDecimal cashOut = new BigDecimal(partes[4].trim());
                                java.math.BigDecimal closing = new BigDecimal(partes[5].trim());

                                Balance balance = new Balance(accountNumber, fecha, descripcion, cashIn, cashOut, closing);

                                balanceService.save(balance);
                                System.out.println("Balance creado");
                            } catch (Exception ex) {
                                System.out.println("Error al crear Balance: " + ex.getMessage());
                            }
                        }
                    }
                    
                    
                    /*Account account = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001", "Savings", "Calle 20 de Turbaco-Bolivar"); 
                    accountService.save(account); */
                    break;
                case "2":
                    System.out.print("[" + entityName + "] Leer por id - ingrese id: ");
                    String id = sc.nextLine().trim();
                    System.out.println("Buscar " + entityName + " con id=" + id + " - placeholder");
                    accountService.findById(id).ifPresentOrElse(
                        acc -> System.out.println("Encontrado: " + acc),
                        () -> System.out.println(entityName + " con id=" + id + " no encontrado.")
                    );
                    break;
                case "3":
                    System.out.println("[" + entityName + "] Listar todos - placeholder");
                    accountService.findAll().stream().forEach(System.out::println);
                    break;
                case "4":
                    System.out.print("[" + entityName + "] Actualizar - ingrese id: ");
                    String idUp = sc.nextLine().trim();
                    System.out.println("Actualizar " + entityName + " id=" + idUp + " - placeholder");
                    //Deben tomar los datos por consola, usar Scanner
                    Account updateAccount = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001", "Savings", "Calle 20 de Turbaco-Bolivar"); 
                    accountService.save(updateAccount);
                    break;
                case "5":
                    System.out.print("[" + entityName + "] Eliminar - ingrese id: ");
                    String idDel = sc.nextLine().trim();
                    System.out.println("Eliminar " + entityName + " id=" + idDel + " - placeholder");
                    accountService.deleteById(idDel);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
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
