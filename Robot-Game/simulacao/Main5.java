package simulacao;

import java.util.ArrayList;
import java.util.Scanner;
import robos.RoboMemoria;
import robos.RoboEstrategico;
import obstaculos.Obstaculo;
import obstaculos.Bomba;
import obstaculos.Rocha;
import excecoes.MovimentoInvalidoException;

public class Main5 {
     public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) throws Exception {

        // --- Inicializando robos e utilizaveis ---
        Scanner teclado = new Scanner(System.in);
        RoboMemoria r1 = new RoboMemoria("Azul");
        RoboEstrategico r2 = new RoboEstrategico("Verde");
        
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
        r2.setAlimentoEst(alimentoX,alimentoY);

        
        // --- Alocação de bombas e rochas ---
        ArrayList<Obstaculo> obstaculos = new ArrayList<>();
        System.out.println("|=== SELEÇÃO DE OBSTÁCULOS ===|");
        System.out.println("* A simulação terá no máximo 2 bombas e 2 rochas");
        System.out.println("* Obstáculos a posição do alimento ou a posição inicial(0,0)");
        System.out.println("* Digite dois inteiros seguidos e pressione Enter. O primeiro número é referente a linha e o segundo a coluna");
        System.out.println("* Inserir um ID inválido indicará que não deseja incluir o determinado obstáculo");
        System.out.println("* Obstáculos colocados fora dos limites 0-3 não serão considerados");
        
        System.out.print("Escolha a posição da primeira BOMBA(Ex: 12): ");
        String id = teclado.nextLine();
        if (!id.matches("\\d+") || id.length() > 2 || id.equals("" + alimentoY + alimentoX) || id.equals("00")){
            System.out.println("*: Obstáculo em posição inválida -> Descartado");
        } else obstaculos.add(new Bomba(id));

        System.out.print("Escolha a posição da segunda BOMBA(Ex: 12): ");
        id = teclado.nextLine();
        if (!id.matches("\\d+") || id.length() > 2 || id.equals("" + alimentoY + alimentoX) || id.equals("00")){
            System.out.println("*: Obstáculo em posição inválida -> Descartado");
        } else obstaculos.add(new Bomba(id));

        System.out.print("Escolha a posição da primeira ROCHA(Ex: 12): ");
        id = teclado.nextLine();
        if (!id.matches("\\d+") || id.length() > 2 || id.equals("" + alimentoY + alimentoX) || id.equals("00")){
            System.out.println("*: Obstáculo em posição inválida -> Descartado");
        } else obstaculos.add(new Rocha(id));

        System.out.print("Escolha a posição da segunda ROCHA(Ex: 12): ");
        id = teclado.nextLine();
        if (!id.matches("\\d+") || id.length() > 2 || id.equals("" + alimentoY + alimentoX) || id.equals("00")){
            System.out.println("*: Obstáculo em posição inválida -> Descartado");
        } else obstaculos.add(new Rocha(id));
        
        

        // --- Inicializando matriz visual ---
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
             if (r1.getVida() && !r1.verificaAlimento(alimentoX, alimentoY)){

                
                matriz[r1.getPosicaoY()][r1.getPosicaoX()] = '*';
                
               
                try {
                    r1.mover();
                } catch (MovimentoInvalidoException e){
                    System.out.println(e.getMessage());
                }      

                for (Obstaculo o : obstaculos){
                    o.bater(r1);
                }
                matriz[r1.getPosicaoY()][r1.getPosicaoX()] = 'B';
                matriz[r2.getPosicaoY()][r2.getPosicaoX()] = 'G';
                printarMatiz(matriz);
                System.out.println(r1);
                r1.printarMemoŕia();
                System.out.println(r2 + "\n");
             } else {
                System.out.println("Robô não pode se mover pois foi explodido ou encontrou o alimento.");
            }
             
            
             
            
            
            // --- Vez robo 2 ---

            System.out.println("|=== MOVIMENTO DO ROBÔ "+r2.cor.toUpperCase() + " ===|");
            System.out.print("* Resultado : ");
            if (r2.getVida() && !r2.verificaAlimento(alimentoX, alimentoY)){
                    matriz[r2.getPosicaoY()][r2.getPosicaoX()] = '*';
                    try {
                        r2.mover();
                    }  catch (MovimentoInvalidoException e){
                        System.out.println(e.getMessage());
                    }
                    for (Obstaculo o : obstaculos){
                        o.bater(r2);
                    }
                    matriz[r1.getPosicaoY()][r1.getPosicaoX()] = 'B';
                    matriz[r2.getPosicaoY()][r2.getPosicaoX()] = 'G';
                    printarMatiz(matriz);
                    System.out.println(r1);
                    System.out.println(r2 + "\n");
            } else {
                System.out.println("Robô não pode se mover pois foi explodido ou encontrou o alimento.\n");
            }
            
            if (r1.verificaAlimento(alimentoX, alimentoY)) r1.setVida(false);
            if (r2.verificaAlimento(alimentoX, alimentoY)) r2.setVida(false);
            
        } while ((!r1.verificaAlimento(alimentoX, alimentoY) || !r2.verificaAlimento(alimentoX, alimentoY)) && (r1.getVida() || r2.getVida()));
        

        System.out.println("|=== SIMULAÇÃO FINALIZADA ===|");
        System.out.print("* Resultado: ");
        if (r1.verificaAlimento(alimentoX, alimentoY) && r2.verificaAlimento(alimentoX, alimentoY)){
            System.out.println("Ambos os robôs encontram o alimento");
        
        } else if (r1.verificaAlimento(alimentoX, alimentoY) && !r2.verificaAlimento(alimentoX, alimentoY)) {
            System.out.println("Apenas o robô " + r1.cor + " encontrou o alimento");
        } else if (!r1.verificaAlimento(alimentoX, alimentoY) && r2.verificaAlimento(alimentoX, alimentoY)) {
            System.out.println("Apenas o robô " + r2.cor + " encontrou o alimento");
        }else {
            System.out.println("Ambos os robôs explodiram em bombas.");
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
