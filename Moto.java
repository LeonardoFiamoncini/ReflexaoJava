import java.util.Scanner;

public class Moto extends MeioTransporte{

	Scanner input = new Scanner(System.in);

	private float velocidadeMax = 130.0f;

    Moto(){  super();  }

    Moto(String modelo){  super(modelo);  }

    Moto(String modelo, String cor){  super(modelo, cor);  }

	@Override
	public String toString(){
		return "\nModelo: " + modelo + "\nCor: " + cor + 
			"\nVelocidade Atual: " + velocidadeAtual + 
			" km/h\nVelocidade Máxima: " + velocidadeMax + " km/h";
	}
				
	public void setVelocidade(float velocidade) {

		if (velocidade > velocidadeMax) velocidade = velocidadeMax;
		else if (velocidade < 0) velocidade = 0;

		if (velocidade == velocidadeAtual) 
			System.out.println("\n" + modelo + " permanece a " + velocidadeAtual + " km/h");
		else {
			velocidadeAtual = velocidade;
			System.out.println("\n" + modelo + " agora está a " + velocidadeAtual + " km/h");
		}				
	}	
    
    public float getVelocidadeMax(){  return velocidadeMax;  }
}


