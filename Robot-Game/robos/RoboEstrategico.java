package robos;

import java.util.Random;
import excecoes.MovimentoInvalidoException;

public class RoboEstrategico extends Robo {
// Esse robô conhece a posição do alimento e sempre escolhe
//  o próximo movimento tentando diminuir a distância até ele.
//  Exemplo: se o alimento está mais à direita, ele tende a mover para right;
//  se está acima, tende a mover para up. 
// Observação: o RoboEstrategico não conhece a posição das bombas 
// nem das rochas.
//  Portanto, pode colidir com obstáculos ou explodir. 
    private int AlimentoEstX;
    private int AlimentoEstY;
    public RoboEstrategico(String cor){
        super(cor);
        this.AlimentoEstX = -1;
        this.AlimentoEstY= -1;
    }
     public void mover () throws MovimentoInvalidoException{
         
        this.antigoX = posicaoX;
        this.antigoY = posicaoY;
        int novaPosicaoY = posicaoY;
        int novaPosicaoX = posicaoX;
        int direcao = -1;



       if (AlimentoEstY > posicaoY && AlimentoEstX > posicaoX) {
            direcao = choose(1, 3);
        } else if (AlimentoEstY > posicaoY && AlimentoEstX < posicaoX) {
            direcao = choose(1, 4);
        } else if (AlimentoEstY < posicaoY && AlimentoEstX > posicaoX) {
            direcao = choose(2, 3);
        } else if (AlimentoEstY < posicaoY && AlimentoEstX < posicaoX) {
            direcao = choose(2, 4);
        } else if (AlimentoEstY > posicaoY) {
            direcao = 1;
        } else if (AlimentoEstY < posicaoY) {
            direcao = 2;
        } else if (AlimentoEstX > posicaoX) {
            direcao = 3;
        } else if (AlimentoEstX < posicaoX) {
            direcao = 4;
        }
        

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
            throw new MovimentoInvalidoException(direcao);
        }
        movimentosValidos++;
        posicaoY = novaPosicaoY;
        posicaoX = novaPosicaoX;
        System.out.println("Robô se moveu na direção " + direcao);
    }

        public void setAlimentoEst(int alimentoX, int alimentoY){
            this.AlimentoEstX = alimentoX;
             this.AlimentoEstY = alimentoY;
        }
        public static int choose(int a, int b){
            return new Random().nextBoolean() ? a : b;
        }
     }
