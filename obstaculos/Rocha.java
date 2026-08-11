package obstaculos;

import robos.Robo;

public class Rocha extends Obstaculo{
 
  public Rocha(String id){
    super(id);
  }
  public void bater(Robo bot){
    if(this.id.equals("" + bot.posicaoY + bot.posicaoX)){
      bot.posicaoX = bot.antigoX;
      bot.posicaoY = bot.antigoY;
      System.out.println("* Ocorrido: O robô " + bot.cor + " bateu em uma rocha e voltou para onde estava");
    }
  }
}
