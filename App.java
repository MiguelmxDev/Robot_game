import java.util.Scanner;

public class App {
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        Robo r1 = new Robo("Azul");
        


        System.out.println("--- Escolha a posição do alimento ---");
        System.out.print("Linha: ");
        int alimentoY = teclado.nextInt();
        if (alimentoY < 0 || alimentoY > 3){
            System.out.println("Linha inválida, deve ser maior que 0 e menor que 4");
            return;
        }
        System.out.print("Coluna: ");
        int alimentoX = teclado.nextInt();
        if (alimentoX < 0 || alimentoX > 3){
            System.out.println("Coluna inválida, deve ser maior que 0 e menor que 4");
            return;
        }

        
        char matriz[][] = new char[4][4];

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                matriz[i][j] = '0';
            }
        }
        matriz[0][0] = 'R';
        matriz[alimentoY][alimentoX] = 'A';

        printarMatiz(matriz);
        int direcao = -1;
        do {
            direcao = teclado.nextInt();
            matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '0';
            try {
                r1.mover(direcao);
            }  catch (MovimentoInvalidoException e){
                System.out.println(e);
            }
            matriz[r1.getPosicaoY()][r1.getPosicaoX()] = 'R';
            printarMatiz(matriz);
            System.out.println(r1);
        } while (!r1.verificaAlimento(alimentoX, alimentoY));
        
        System.out.println("Alimento encontrado!");
   }

   public static void printarMatiz(char matriz[][]){
        for (int i = 3; i >= 0; i--){
            for (int j = 0; j < 4; j++){
                if (matriz[i][j] == 'R'){
                    System.out.print(BLUE + 'R' + RESET+ " ");
                } else if (matriz[i][j] == 'A'){
                    System.out.print(RED + 'A' + RESET + " ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
                
            }
            System.out.println();
        }
   }
}
