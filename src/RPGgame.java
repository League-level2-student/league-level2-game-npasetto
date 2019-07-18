import java.awt.Dimension;

import javax.swing.*;

public class RPGgame {
JFrame window;
public static final int WIDTH=500;
public static final int HEIGHT=700;
GamePanel panel=new GamePanel();
public static void main(String[] args) {
	RPGgame r = new RPGgame();
	r.setup();
}
public RPGgame() {
	window=new JFrame();
}
public void setup() {
	window.add(panel);
	window.addKeyListener(panel);
	window.addMouseListener(panel);
	window.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.pack();
}
}
