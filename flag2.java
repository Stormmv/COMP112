//make flag//

import ecs100.*;
import java.awt.Color;

/*draw the Greenland flag and make the width adjustable */

public class flag2{

    public static void main(String[] args){
        new flag2();
    }

    public flag2(){
        UI.addButton("Draw Greenland", this::drawGreenland);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);
    }

    public void drawGreenland(){
        UI.clearGraphics();
        double width = UI.askDouble("What width?");
        double height = width * 2/3;
        double x = 200;
        double y = 150;
        UI.setcolor(Color.red);
        UI.fillRect(x, y*2, width, height/2);
        UI.setcolor(Color.white);
        UI.fillRect(x, y, width, height/2);
        UI.setcolor(Color.red);
        UI.drawArc(x/2, y/2, width/3, height/3, 0, 180);
    }

}
