# 🧾 Parcial 1 — Programación Orientada a Objetos

## ✅ Instrucciones Generales

Para completar este parcial, sigue los siguientes pasos:

1. 🔀 Hacer un **fork** de esta rama en tu repositorio personal de GitHub.
2. 💻 Hacer un **git clone** de tu repositorio en tu computador local.
3. 🧠 Realizar el código solicitado en la sección de [REQUERIMIENTOS](#requerimientos).
4. 🚀 Hacer un **push** desde tu computador a tu repositorio personal.
5. 📥 Crear un **Pull Request** hacia el repositorio original (repositorio del profesor).

---

## 📌 REQUERIMIENTOS

### 1. Clase `Cuenta`

Crear una clase `Cuenta` con los siguientes atributos **encapsulados**:

```java
private String numeroCuenta;
private long dniCliente;
private double saldoActual;
```

### 2. Clase `Ahorro y Corriente`

Crear dos clases Ahorro y Corriente, estos deben heredar de la clase cuenta, dos constructores (uno vacio y otro con todos los atributos), los atributos deben estar encapsulados, los atributos adicionales son:

1. 
	Ahorro --> String fechaCreacion, implementar el metodo toString() para que imprima todos los campos (Hasta los que hereden)
2. 
    Corriente --> double impuesto, implementar el metodo toString() para que imprima todos los campos (Hasta los que hereden)

### 3. Interface y Clase `IServiceCuenta - ServiceCuenta`
1. 
    Implementar en la clase ServiceCuenta los metodos de la interface IServiceCuenta

### 4. Clase Principal `App`
4. Crear en la clase principal un menu con las siguientes opciones:
	a. Listar todas las cuentas Ahorro
	b. Listar todas las cuentas Corriente
	c. Crear cuenta de Ahorro
	d. Crear cuenta Corriente
	e. Obtener la información de la cuenta por el numero de la cuenta