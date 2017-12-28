package Observer;

public interface Observable {
  //register given object
  public boolean register(Observer o);
  //unregister given object
  public boolean unregister(Observer o);

  public void notifyObservers();
}
