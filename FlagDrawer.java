import ecs100.*;
import java.awt.Color;

public class FlagDrawer{

    public static void main(String[] args){
        new FlagDrawer();
    }

    public FlagDrawer(){
        UI.addButton("Draw UAE", this::drawUAE);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);
    }

    public void drawUAE(){
        UI.clearGraphics();
        double width = UI.askDouble("What width?");
        double height = width * 2/3;
        double x = 100;
        double y = 50;
        UI.setColor(Color.red);
        UI.fillRect(x, y, width/4, height);
        UI.setColor(new Color(34,150,34));
        UI.fillRect(x + width/4, y, width/4 *3, height/3);
        UI.setColor(Color.white);
        UI.fillRect(x + width/4, y + height/3, width/4 *3, height/3);
        UI.setColor(Color.black);
        UI.fillRect(x + width/4, y + height/3 *2, width/4 *3, height/3);
        UI.setColor(Color.black);
        UI.drawRect(x, y, width, height);
    }
  }

   