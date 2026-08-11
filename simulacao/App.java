package simulacao;

import java.util.Scanner;
import robos.Robo;
import excecoes.MovimentoInvalidoException;

public class App {
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        Robo r1 = new Robo("Azul");
        


       // --- Seleçaão do alimento ---
       int alimentoX = -1;
       int alimentoY = -1;
       do{
           System.out.println("--- Selecione a posição do alimento ---");
           System.out.print("Linha(0-3): ");
           alimentoY = teclado.nextInt();
           while (alimentoY < 0 || alimentoY > 3){
               System.out.println("Linha inválida, deve ser positiva e menor que 4");
               System.out.print("Linha(0-3): ");
               alimentoY = teclado.nextInt();
           }
           System.out.print("Coluna(0-3): ");
           alimentoX = teclado.nextInt();
           while (alimentoX < 0 || alimentoX > 3){
               System.out.println("Coluna inválida, deve ser positiva e menor que 4");
               System.out.print("Coluna(0-3): ");
               alimentoX = teclado.nextInt();
           }
       } while (alimentoX == 0 && alimentoY == 0);
        teclado.nextLine();

        
        char matriz[][] = new char[4][4];

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                matriz[i][j] = '*';
            }
        }
        matriz[0][0] = 'B';
        matriz[alimentoY][alimentoX] = 'A';

        printarMatiz(matriz);
      
        do {
            String direcao = teclado.nextLine().toLowerCase();
            
            
            matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '*';

            try {
                int direcaoInt = Integer.parseInt(direcao);
                r1.mover(direcaoInt);
            } catch (NumberFormatException e) {
                try {
                    r1.mover(direcao);
                } catch (MovimentoInvalidoException e2) {
                    System.out.println(e2);
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println(e);
            }
            matriz[r1.getPosicaoY()][r1.getPosicaoX()] = 'B';
            printarMatiz(matriz);
            System.out.println(r1);
        } while (!r1.verificaAlimento(alimentoX, alimentoY));
        
        System.out.println("Alimento encontrado!");
        teclado.close();

   }

   public static void printarMatiz(char matriz[][]){
    System.out.println("---------");
    
    for (int i = 3; i >= 0; i--){
        System.out.print(i + " ");
        for (int j = 0; j < 4; j++){
            if (matriz[i][j] == 'B'){
                System.out.print(BLUE + 'B' + RESET + " ");
            }else if (matriz[i][j] == 'A'){
                System.out.print(RED + 'A' + RESET + " ");
            } else {
                System.out.print(matriz[i][j] + " ");
            }
            
        }
        
        System.out.println();
    }
    System.out.println("+ 0 1 2 3");
    System.out.println("---------");
}
}
