package simulacao;

import java.util.Random;
import java.util.Scanner;
import robos.Robo;
import excecoes.MovimentoInvalidoException;

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

        
        // --- Loop simulação ---
        System.out.println("|=== INICIALIZANDO SIMULAÇÃO...===|");
        System.out.println("|=== ESTADO INICIAL ===|");
        printarMatiz(matriz);
        System.out.println(r1);
        System.out.println(r2 + "\n");
        do {
            System.out.print("Pressione enter para continuar...");
            teclado.nextLine();
            // --- Vez robo 1 ---
            System.out.println("\n|=== MOVIMENTO DO ROBÔ "+r1.cor.toUpperCase() + " ===|");
            System.out.print("* Resultado : ");
             

                
                matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '*';
                
                try {
                    r1.mover(dir.nextInt(4) + 1);
                } catch (MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }   

                
                matriz[r1.getPosicaoY()][r1.getPosicaoX()] = 'B';
                matriz[r2.getPosicaoY()][r2.getPosicaoX()] = 'G';
                printarMatiz(matriz);
                System.out.println(r1);
                System.out.println(r2 + "\n");
            
             
            if (r1.verificaAlimento(alimentoX, alimentoY)) break;
             
            
            
            // --- Vez robo 2 ---

            System.out.println("|=== MOVIMENTO DO ROBÔ "+r2.cor.toUpperCase() + " ===|");
            System.out.print("* Resultado : ");
           
                    matriz[r2.getPosicaoY()][r2.getPosicaoX()] = '*';
                    try {
                        r2.mover(dir.nextInt(4) + 1);
                    }  catch (MovimentoInvalidoException e){
                        System.out.println(e.getMessage());
                    }
                    matriz[r1.getPosicaoY()][r1.getPosicaoX()] = 'B';
                    matriz[r2.getPosicaoY()][r2.getPosicaoX()] = 'G';
                    printarMatiz(matriz);
                    System.out.println(r1);
                    System.out.println(r2 + "\n");
            
            
            
            
        } while (!r1.verificaAlimento(alimentoX, alimentoY) && !r2.verificaAlimento(alimentoX, alimentoY));
        
        System.out.println("Alimento encontrado!");
        if(r1.verificaAlimento(alimentoX, alimentoY)){
            System.out.println("Alimento encontrado por Robo 1");
        }
        else if(r2.verificaAlimento(alimentoX, alimentoY)){
            System.out.println("Alimento encontrado por Robo 2");
        }
        System.out.println(r1);
        System.out.println(r2 + "\n");

        teclado.close();
   }

   public static void printarMatiz(char matriz[][]){
    System.out.println("---------");
    
    for (int i = 3; i >= 0; i--){
        System.out.print(i + " ");
        for (int j = 0; j < 4; j++){
            if (matriz[i][j] == 'B'){
                System.out.print(BLUE + 'B' + RESET + " ");
            } else if(matriz[i][j] == 'G'){
                System.out.print(GREEN + 'G' + RESET + " ");
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
