package es.dfleper.HospitalController;

public class HospitalUci {
	private int Pacientes;
	private int Doctores;
	private int Enfermeros;
	private final int MaxNumPacientes = 16;

	public HospitalUci() {
		this.Pacientes = 0;
		this.Doctores = 0;
		this.Enfermeros = 0;
	}

	public synchronized void EntrarPaciente(String NombrePaciente) {

		while ((this.Pacientes >= this.MaxNumPacientes) || (this.Doctores < 1) || (this.Enfermeros < 1)
				|| ((this.Doctores * 4) < this.Pacientes) || ((this.Enfermeros * 2) < this.Pacientes)) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("El " + NombrePaciente + " esta esperando a ser ingresado en UCI.");
		Pacientes++;
		System.out.println("El " + NombrePaciente + " ha sido ingresado en UCI. (Pacientes=" + Pacientes + ", Doctores="
				+ Doctores + ", Enfermeros=" + Enfermeros + ")");
	}

	public synchronized void SalirPaciente(String NombrePaciente) {
		Pacientes--;
		System.out.println("El " + NombrePaciente + " ha salido de la UCI. (Pacientes=" + Pacientes + ", Doctores="
				+ Doctores + ", Enfermeros=" + Enfermeros + ")");
		notifyAll();
	}

	public synchronized void EntrarDoctor(String NombreDoctor) {
		while ((this.Doctores * 4) > this.Pacientes) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("El " + NombreDoctor + " esta a la espera de entrar al servicio UCI.");
		Doctores++;
		System.out.println("El " + NombreDoctor + " ha entrado al servicio UCI. (Pacientes=" + Pacientes + ", Doctores="
				+ Doctores + ", Enfermeros=" + Enfermeros + ")");
	}

	public synchronized void SalirDoctor(String NombreDoctor) {
		Doctores--;
		System.out.println("El " + NombreDoctor + " ha abandonado el servicio UCI. (Pacientes=" + Pacientes
				+ ", Doctores=" + Doctores + ", Enfermeros=" + Enfermeros + ")");
		notifyAll();
	}

	public synchronized void EntrarEnfermero(String NombreEnfermero) {
		while ((this.Enfermeros * 2) > this.Pacientes) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("El " + NombreEnfermero + " esta a la espera de entrar al servicio UCI.");
		Enfermeros++;
		System.out.println("El " + NombreEnfermero + " ha entrado al servicio UCI. (Pacientes=" + Pacientes
				+ ", Doctores=" + Doctores + ", Enfermeros=" + Enfermeros + ")");

	}

	public synchronized void SalirEnfermero(String NombreEnfermero) {
		Enfermeros--;
		System.out.println("El " + NombreEnfermero + " ha abandonado el servicio UCI. (Pacientes=" + Pacientes
				+ ", Doctores=" + Doctores + ", Enfermeros=" + Enfermeros + ")");
		notifyAll();
	}
}
