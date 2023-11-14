package main;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameFrame{
	public GameFrame(GamePanel gamePanel) {
		JFrame frame = new JFrame("Platformer");
		//frame.setSize(400, 400);
		//frame.getContentPane().setPreferredSize(new Dimension(400,400));
		frame.add(gamePanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); //setVisible needs to be at the bottom for some reason
		frame.setResizable(false);
		frame.addWindowFocusListener(new WindowFocusListener(){

			@Override
			public void windowGainedFocus(WindowEvent e) {
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}
			
		});
	}
}

