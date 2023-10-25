package es.dfleper.HospitalController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		System.out.println(" ");
		System.out.println("Maxima Capacidad de la UCI: 16");
		System.out.println("Cantidad de Pacientes: 32, 1 Paciente cada 250 ms");
		System.out.println("Cantidad de Doctores: 8, 1 Doctor cada 3 segundos");
		System.out.println("Cantidad de Enfermeros: 16, 1 Enfermero cada 3 segundos");
		System.out.println("Proporcion 1 Doctor | 4 Pacientes");
		System.out.println("Proporcion 2 Enfermeros | 4 Pacientes");
		System.out.println(" ");
		System.out.println("INICIO DE SIMULACION...");
		System.out.println(" ");

		HospitalUci objHospitalUci = new HospitalUci();

		// Se lanzan 8 hilos de doctores
		for (int i = 1; i <= 8; i++) {
			Doctor objDoctor = new Doctor("Doctor " + i, objHospitalUci);
			objDoctor.setPriority(10);
			objDoctor.start();
		}

		// Se lanzan 16 hilos de enfermeros
		for (int i = 1; i <= 16; i++) {
			Enfermero objEnfermero = new Enfermero("Enfermero " + i, objHospitalUci);
			objEnfermero.setPriority(8);
			objEnfermero.start();
		}

		// Se lanzan 32 hilos de pacientes
		for (int i = 1; i <= 32; i++) {
			Paciente objPaciente = new Paciente("Paciente " + i, objHospitalUci);
			objPaciente.setPriority(1);
			objPaciente.start();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
