package robos;

import excecoes.MovimentoInvalidoException;

public class Robo {
    public int posicaoX;
    public int posicaoY;
    protected int movimentosValidos;
    protected int movimentosInvalidos;
    public String cor;
    protected boolean vida;
    public int antigoX;
    public int antigoY;


    // --- Construtor ---
    public Robo (String cor){
        this.posicaoX = 0;
        this.posicaoY = 0;
        this.antigoX = -1;
        this.antigoY = -1;
        this.movimentosInvalidos = 0;
        this.cor = cor;
        this.vida = true;
    }
   

    // --- Movimentação ---
    public void mover (String direcao) throws MovimentoInvalidoException{
        
        this.antigoX = posicaoX;
        this.antigoY = posicaoY;
        int novaPosicaoY = posicaoY;
        int novaPosicaoX = posicaoX;
        switch (direcao) {
            case "up" -> novaPosicaoY++;
            case "down" -> novaPosicaoY--;
            case "right" -> novaPosicaoX++;
            case "left" -> novaPosicaoX--;
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

    

    public void mover (int direcao) throws MovimentoInvalidoException{
         

        this.antigoX = posicaoX;
        this.antigoY = posicaoY;
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
            throw new MovimentoInvalidoException(direcao);
        }

        movimentosValidos++;
        posicaoY = novaPosicaoY;
        posicaoX = novaPosicaoX;
        System.out.println("Robô se moveu na direção " + direcao);
    
    }
    
    // --- Printar ---
    public String toString(){
        return cor + ": (" + this.posicaoX + "," + this.posicaoY + ") " + "Movimentos inválidos: " + this.movimentosInvalidos + " | Movimentos válidos: " + this.movimentosValidos; 
    }

    // --- Verificar Posição---
    public boolean verificaAlimento(int alimentoX, int alimentoY){
        
        if(posicaoX == alimentoX && posicaoY == alimentoY){
            return true;
        }
        return false;
    }

    // --- Getters & Setters ---
    public int getPosicaoX() {
        return posicaoX;
    }


    public int getPosicaoY() {
        return posicaoY;
    }

    public boolean getVida() {
        return vida;
    }

    public void setVida(boolean vida){
        this.vida = vida;
    }
    

    
}
