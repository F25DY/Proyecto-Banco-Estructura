import java.util.List;

import domain.Ahorro;
import domain.Corriente;
import domain.Cuenta;
import service.ServiceCuenta;

public class App {
    public static void main(String[] args) throws Exception {
        ServiceCuenta serviceCuenta=new ServiceCuenta();
        //solamente debe usar este vector no se puede crear otros
        List<Cuenta> cuentas=serviceCuenta.obtenerCuentas();
    }
}
