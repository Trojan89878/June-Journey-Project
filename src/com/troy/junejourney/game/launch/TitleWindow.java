package com.troy.junejourney.game.launch;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import com.troy.junejourney.game.*;
import com.troy.junejourney.game.settings.*;

public class TitleWindow {

	private static JFrame frame;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void initialize() {
		frame = new JFrame();
		
		frame.setSize(1440, 810);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Troy's Mars Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel gui = new JPanel(new GridLayout(0,1,10,10));
        gui.setBorder(new EmptyBorder(20,30,20,30));

        Insets margin = new Insets(20,150,20,150);
        
        
        JButton playButton = new JButton("Play");
        playButton.setMargin(margin);
        
        playButton.addActionListener(e -> {
        	hide();
    		Main.game = new Game();
        });
        
        gui.add(playButton);
        
        JButton optionsButton = new JButton("Options");
        optionsButton.setMargin(margin);
        
        optionsButton.addActionListener(e ->{
        	SettingsWindow.show();
        });
        
        gui.add(optionsButton);
        
        JButton quitButton = new JButton("Quit");
        quitButton.setMargin(margin);
        
        quitButton.addActionListener(e ->{
        	System.exit(0);
        });
        
        gui.add(quitButton);
        
        
        frame.add(gui);
		
        frame.pack();
        
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
	}
	
	public static void hide(){
		frame.setVisible(false); 
	}
	
	public static void show(){
		frame.setVisible(true);
	}

}
