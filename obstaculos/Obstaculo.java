package obstaculos;

import robos.Robo;

public abstract class Obstaculo {
  protected String id;
  public Obstaculo(String id){
    this.id = id;
  }
  public abstract void bater(Robo bot);
  
}
