import java.util.Random;

public class RoboInteligente extends Robo {

    Random dir = new Random();
    int memoriaInteligente;

    public RoboInteligente(String cor){
        super(cor);
        this.memoriaInteligente = -1;
    }

    @Override
    public void mover (int direcao) throws MovimentoInvalidoException{

        while (direcao == memoriaInteligente) {
            System.out.println("Receba a inteligencia");
            direcao = dir.nextInt(4) + 1;
        }
         
        int novaPosicaoY = posicaoY;
        int novaPosicaoX = posicaoX;
        switch (direcao) {
            case 1 -> novaPosicaoY++;
            case 2 -> novaPosicaoY--;
            case 3 -> novaPosicaoX++;
            case 4 -> novaPosicaoX--;
            default ->{
                System.out.println("Direção inválida!");
                return;
            }
        }

        if (novaPosicaoY < 0 || novaPosicaoX < 0 || novaPosicaoY > 3 || novaPosicaoX > 3){
            movimentosInvalidos++;
            memoriaInteligente = direcao;
            throw new MovimentoInvalidoException();
        }
        
        memoriaInteligente = -1;
        movimentosValidos++;
        posicaoY = novaPosicaoY;
        posicaoX = novaPosicaoX;
    }
}
