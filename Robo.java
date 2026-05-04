public class Robo {
    protected int posicaoX;
    protected int posicaoY;
    protected int movimentosValidos;
    protected int movimentosInvalidos;
    protected String cor;


    // --- Construtor ---
    public Robo (String cor){
        this.posicaoX = 0;
        this.posicaoY = 0;
        this.movimentosInvalidos = 0;
        this.cor = cor;
    }
   

    // --- Movimentação ---
    public void mover (String direcao) throws MovimentoInvalidoException{

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
            throw new MovimentoInvalidoException();
        }

        movimentosValidos++;
        posicaoY = novaPosicaoY;
        posicaoX = novaPosicaoX;
    }

    

    public void mover (int direcao) throws MovimentoInvalidoException{
         
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
            throw new MovimentoInvalidoException();
        }

        movimentosValidos++;
        posicaoY = novaPosicaoY;
        posicaoX = novaPosicaoX;
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

    // --- Getters ---
    public int getPosicaoX() {
        return posicaoX;
    }


    public int getPosicaoY() {
        return posicaoY;
    }
    

    
}
