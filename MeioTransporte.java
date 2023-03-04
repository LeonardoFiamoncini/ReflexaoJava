import java.util.Scanner;

public class MeioTransporte {

    Scanner input = new Scanner(System.in);
    
    public String modelo, cor;
	public float velocidadeAtual = 0.0f;

    MeioTransporte(){}
    
    MeioTransporte(String modelo){  this.modelo = modelo;  }

    MeioTransporte(String modelo, String cor){
        this.modelo = modelo;
        this.cor = cor;
    }

    public void setModelo(String modelo){
        System.out.println("\nO modelo " + this.modelo + " passa agora a ser " + modelo + ".");
        this.modelo = modelo;
    }

    public void setCor(String cor){
		System.out.println("\n"+ modelo + " trocou a cor de " + this.cor + " para " + cor + ".");
		this.cor = cor;
	}

    public String getModelo(){  return modelo;  }

    public String getCor(){  return cor;  }

    public float getVelocidadeAtual(){  return velocidadeAtual;  }
}


