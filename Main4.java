import java.util.Random;
import java.util.Scanner;

public class Main4 {
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        Random dir = new Random();
        Robo r1 = new Robo("Azul");
        Robo r2 = new RoboInteligente("Verde");
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
        // Alocação de bombas e rochas
        System.out.println("Escolha a posição da primeira bomba(Ex: 12): ");
        Bomba b1 = new Bomba(teclado.nextLine());
        System.out.println("Escolha a posição da segunda bomba(Ex: 12): ");
        Bomba b2 = new Bomba(teclado.nextLine());
        System.out.println("Escolha a posição da primeira rocha(Ex: 12): ");
        Rocha rch1 = new Rocha(teclado.nextLine());
        System.out.println("Escolha a posição da segunda rocha(Ex: 12): ");
        Rocha rch2 = new Rocha(teclado.nextLine());

        // A partir daqui o código é igual ao da main3
        char matriz[][] = new char[4][4];

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                matriz[i][j] = '0';
            }
        }
        matriz[0][0] = '1';
        matriz[alimentoY][alimentoX] = 'A';

        System.out.println("|--- Inicializando simulação---|");
        do {
            teclado.nextLine();
            // Vez robo 1
            if (!r1.verificaAlimento(alimentoX, alimentoY)){
                System.out.println("Movimento do robô 1: ");
                direcao = dir.nextInt(4) + 1;
                matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '0';
                try {
                    r1.mover(direcao);
                } catch (MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }

                matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '1';
                matriz[r2.getPosicaoY()][r2.getPosicaoX()] = '2';
                printarMatiz(matriz);
                System.out.println(r1 + "\n");
            }
            
            
            // Vez robo 2
            if (!r2.verificaAlimento(alimentoX, alimentoY)){
                System.out.println("Movimento do robô 2: ");
                direcao = dir.nextInt(4) + 1;
                matriz[r2.getPosicaoY()][r2.getPosicaoX()] = '0';
                try {
                    r2.mover(direcao);
                }  catch (MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }
                matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '1';
                matriz[r2.getPosicaoY()][r2.getPosicaoX()] = '2';
                printarMatiz(matriz);
            
                System.out.println(r2 + "\n");
            }
            
            
        } while (!r1.verificaAlimento(alimentoX, alimentoY) || !r2.verificaAlimento(alimentoX, alimentoY));
        
        System.out.println("Ambos os robôs encontraram o alimento");
        System.out.println(r1);
        System.out.println(r2);
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
