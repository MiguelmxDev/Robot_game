public class Rocha extends Obstaculo{
  private int antigoX = 1;
  private int antigoY = 1;
  public Rocha(String id){
    super(id);
  }
  public void bater(Robo bot){
    if(this.id.equals("" + bot.posicaoX + bot.posicaoY)){
      bot.posicaoX = antigoX;
      bot.posicaoY = antigoY;
    }
    antigoX = bot.posicaoX;
    antigoY = bot.posicaoY;
  }
}
