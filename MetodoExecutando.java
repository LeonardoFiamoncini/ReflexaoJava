import java.util.Scanner;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class MetodoExecutando {
    public static void main(String args[]) throws ClassNotFoundException {
		Scanner input = new Scanner(System.in);
		try { 
			Class<?> veiculo1 = Class.forName(opcaoVeiculo(1));
			Class<?> veiculo2 = Class.forName(opcaoVeiculo(2));
			try {			
				Constructor c1 = veiculo1.getDeclaredConstructors()[0];
				Constructor c2 = veiculo2.getDeclaredConstructors()[0];
				try {
					Object v1 = criacaoVeiculo(c1, 1);
					Object v2 = criacaoVeiculo(c2, 2);

					System.out.println("\nDados do 1º veículo (" + v1.getClass() + ")\n" + v1);
					System.out.println("\nDados do 2º veículo (" + v2.getClass() + ")\n" + v2);

					initVelocidade(v1, 1);
					initVelocidade(v2, 2);

					System.out.println("\nDados do 1º veículo (" + v1.getClass() + ")\n" + v1);
					System.out.println("\nDados do 2º veículo (" + v2.getClass() + ")\n" + v2);

					System.out.println("\nDeseja iniciar a simulação?");
					System.out.print("\n1) Sim\n2) Não\nOpção (Digite apenas 1 ou 2): ");
					byte opcao = input.nextByte();

					if (opcao == 1){
						if (diferencaVelocidade(v1, v2) != 0){

							Object vMenorVel;
							Object vMaiorVel;
							if (veiculoVelAtual(v1) < veiculoVelAtual(v2)){
								vMenorVel = v1;
								vMaiorVel = v2;
							} else {
								vMenorVel = v2;
								vMaiorVel = v1;
							} 

							while (diferencaVelocidade(v1, v2) != 0.0f){
	
								System.out.println("\n\n");
							
								if (diferencaVelocidade(v1, v2) <= 2) alteraVelocidades(1, vMenorVel, vMaiorVel);
								else if (diferencaVelocidade(v1, v2) < 14) alteraVelocidades(2, vMenorVel, vMaiorVel);
								else if (diferencaVelocidade(v1, v2) < 28) alteraVelocidades(6, vMenorVel, vMaiorVel);
								else alteraVelocidades(8, vMenorVel, vMaiorVel);
								
								try {
									TimeUnit.SECONDS.sleep(2);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
		
							}
	
							System.out.println("\n\nOs veículos atingiram a mesma velocidade!");
	
						} else System.out.println("\n\nOs veículos já se encontram na mesma velocidade!");

						System.out.println("\nDados do 1º veículo (" + v1.getClass() + ")\n" + v1);
						System.out.println("\nDados do 2º veículo (" + v2.getClass() + ")\n" + v2);
					} 

					System.out.println("\n\nSaindo do programa...");
						
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}							
			} catch (SecurityException e) {
				e.printStackTrace();
			}	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}	

	public static void alteraVelocidades(int vel, Object vcl1, Object vcl2){
		Method metodo1;
		Method metodo2;
		try {
			if (diferencaVelocidade(vcl1, vcl2) != 1){
				metodo1 = acharMetodoPeloNome(vcl1.getClass(), "setVelocidade");
				metodo1.invoke(vcl1, veiculoVelAtual(vcl1) + vel);
			} else {
				metodo1 = acharMetodoPeloNome(vcl1.getClass(), "setVelocidade");
				metodo1.invoke(vcl1, veiculoVelAtual(vcl1));
			}
			metodo2 = acharMetodoPeloNome(vcl2.getClass(), "setVelocidade");
			metodo2.invoke(vcl2, veiculoVelAtual(vcl2) - vel);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public static float diferencaVelocidade(Object v1, Object v2){
		float v1VelAtual = veiculoVelAtual(v1);
		float v2VelAtual = veiculoVelAtual(v2);

		if (v1VelAtual < v2VelAtual) return (v2VelAtual - v1VelAtual);
		else if (v1VelAtual > v2VelAtual) return (v1VelAtual - v2VelAtual); 
		else return 0.0f;
	}

	public static void initVelocidade(Object v, int n){
		Scanner input = new Scanner(System.in);
		
		System.out.print("\nDigite uma velocidade inicial para o " + n + "º veículo: ");
		float vel = input.nextFloat();

		try {
			Method m = acharMetodoPeloNome(v.getClass(), "setVelocidade");
			m.invoke(v, vel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object criacaoVeiculo(Constructor c, int n){
		Scanner input = new Scanner(System.in);

		System.out.print("\nDigite o modelo do " + n + "º veículo: ");
		String modelo = input.nextLine();

		System.out.print("\nAgora informe a cor desejada para " + modelo + ": ");
		String cor = input.nextLine();

		try {
			Object v = c.newInstance(new Object[] { modelo, cor });
			return v;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String opcaoVeiculo(int n){
		Scanner input = new Scanner(System.in);

		System.out.print("\nEscolha o tipo de transporte do " + n + "º veículo: ");
		System.out.print("\n1) Carro\n2) Moto\nOpção (Digite apenas 1 ou 2): ");
		byte opcao = input.nextByte();

		switch(opcao){
			case 1: return "Carro"; 
			case 2: return "Moto";
			default: System.out.println("\nERRO! Opção inexistente."); return null;
		} 
	}

	public static Method acharMetodoPeloNome(Class<?> c, String nome) throws Exception {
		for (Method metodo : c.getMethods()) {
			if (metodo.getName().equals(nome)) return metodo;
		}
		throw new Exception("Método " + nome + " não encontrado.");
	}

	public static float veiculoVelAtual(Object v){
		Field campoVelAtual;
		try {
			campoVelAtual = (v.getClass()).getField("velocidadeAtual");
			try {
				float velAtual = (float) campoVelAtual.get(v);
				return velAtual;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return 0.0f;
	}
}
