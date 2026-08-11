package robos;

import java.util.ArrayList;
import java.util.Random;
import excecoes.MovimentoInvalidoException;

public class RoboMemoria extends Robo {
    private ArrayList<String> visitadas;
    private Random dir = new Random();
    public RoboMemoria(String cor){
        super(cor);
        this.visitadas = new ArrayList<String>();
        visitadas.add("00");
    }


    public void mover() throws MovimentoInvalidoException{
        
        this.antigoX = posicaoX;
        this.antigoY = posicaoY;
        int contador = 0;
        while(true){
            int direcao = dir.nextInt(4) + 1;
            if (contador > 3 ) {
                visitadas.clear();
                System.out.println("* Ocorrido: Robô preso, resetando memória...");
            }
            int novaPosicaoY = posicaoY;
            int novaPosicaoX = posicaoX;
            switch (direcao) {
                case 1 -> novaPosicaoY++;
                case 2 -> novaPosicaoY--;
                case 3 -> novaPosicaoX++;
                case 4 -> novaPosicaoX--;
            }

            if (novaPosicaoY < 0 || novaPosicaoX < 0 || novaPosicaoY > 3 || novaPosicaoX > 3){
            movimentosInvalidos++;
            
            throw new MovimentoInvalidoException(direcao);
            }

            String novaPos = novaPosicaoY + "" + novaPosicaoX;
             
            if(!visitadas.contains(novaPos)){
                visitadas.add(novaPos);
                posicaoX = novaPosicaoX;
                posicaoY = novaPosicaoY;
                movimentosValidos++;
                System.out.println("Robô se moveu na direção " + direcao);
                break;
            } else {
                contador++;
            }
            
            
        }
        return;
    }

    public void printarMemoŕia(){
        System.out.print("Casas visitadas: ");
        for (String s : visitadas){
            System.out.print("(" + s + ") ");
        }
        System.out.println();
    }
}
