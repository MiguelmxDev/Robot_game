public class Bomba extends Obstaculo {
  private boolean ativa = true;
  public Bomba(String id){
    super(id);
  }
  public void bater(Robo bot){
    if(!ativa){
      return;
    }
    if (this.id.equals("" + bot.posicaoX + bot.posicaoY)){
      bot = null;
      ativa = false;
    }
  }
}
