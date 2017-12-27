package Observer;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

public class PriceWatcher implements Observer{
  private String name;

  public PriceWatcher(String name){
    this.name = name;
  }

  public void update(){
    System.out.println("Data Updated");
    JOptionPane.showMessageDialog(null, "Would you like to pull down updated data?");
  }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
