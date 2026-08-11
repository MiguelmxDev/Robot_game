package excecoes;

public class MovimentoInvalidoException extends Exception {
    public String toString(){
        return "Movimento inválido!";
    }

    public MovimentoInvalidoException(int direcao){
        super("Movimento inválido! Robô tentou ir na direção " + direcao);
    }

    public MovimentoInvalidoException(String direcao){
        super("Movimento inválido! Robô tentou ir na direção " + direcao);
    }
}
