import java.util.Random;
import java.util.Scanner;

public class Main2 {
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        Random dir = new Random();
        Robo r1 = new Robo("Azul");
        Robo r2 = new Robo("Verde");
        int direcao = -1;


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
        matriz[0][0] = '1';
        matriz[alimentoY][alimentoX] = 'A';

        
        do {
            // Vez robo 1
            teclado.nextLine();
            direcao = dir.nextInt(4) + 1;
            matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '0';
            try {
                r1.mover(direcao);
            }  catch (MovimentoInvalidoException e){
                System.out.println(e);
            }

            matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '1';
            matriz[r2.getPosicaoY()][r2.getPosicaoX()] = '2';
            printarMatiz(matriz);
            System.out.println(r1);

            // Vez robo 2
            direcao = dir.nextInt(4) + 1;
            matriz[r2.getPosicaoY()][r2.getPosicaoX()] = '0';
            try {
                r2.mover(direcao);
            }  catch (MovimentoInvalidoException e){
                System.out.println(e);
            }
            matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '1';
            matriz[r2.getPosicaoY()][r2.getPosicaoX()] = '2';
            printarMatiz(matriz);
            
            System.out.println(r2);
            
        } while (!r1.verificaAlimento(alimentoX, alimentoY) && !r2.verificaAlimento(alimentoX, alimentoY));
        
        System.out.println("Alimento encontrado!");
        if(r1.verificaAlimento(alimentoX, alimentoY)){
            System.out.println("Alimento encontrado por Robo 1");
        }
        else if(r2.verificaAlimento(alimentoX, alimentoY)){
            System.out.println("Alimento encontrado por Robo 2");
        }
   }

   public static void printarMatiz(char matriz[][]){
        for (int i = 3; i >= 0; i--){
            for (int j = 0; j < 4; j++){
                if (matriz[i][j] == '1'){
                    System.out.print(BLUE + '1' + RESET + " ");
                } else if(matriz[i][j] == '2'){
                    System.out.print(GREEN + '2' + RESET + " ");
                }else if (matriz[i][j] == 'A'){
                    System.out.print(RED + 'A' + RESET + " ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
                
            }
            System.out.println();
        }
   }
}
