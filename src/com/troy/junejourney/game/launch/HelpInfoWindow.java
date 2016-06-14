package com.troy.junejourney.game.launch;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

import com.troy.junejourney.game.*;

public class HelpInfoWindow {

	private static JFrame frame;
	private static boolean open = false;
	private static final Font FONT = new Font("Serif", Font.PLAIN, 18);

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setSize(600, 450);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				hide();
			}
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		frame.setTitle("Help & Info");

		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JLabel titleAndVersion = new JLabel("Mars Game Version " + Game.VERSION);// Serif
		titleAndVersion.setFont(FONT);
		frame.getContentPane().add(titleAndVersion);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(FONT);
		textArea.setEditable(false);
		textArea.setText(Game.INFO);
		textArea.setPreferredSize(new Dimension(400, 120));
		frame.getContentPane().add(textArea);

		JLabel creditsLabel = new JLabel("Credits:");
		creditsLabel.setFont(FONT);
		frame.getContentPane().add(creditsLabel);

		JTextArea creditsText = new JTextArea();
		creditsText.setFont(FONT);
		creditsText.setEditable(false);
		creditsText.setText(Game.CREDITS);
		frame.getContentPane().add(creditsText);

		JPanel panel = new JPanel();
		panel.setFont(FONT);
		frame.getContentPane().add(panel);

		JButton btnNewButton = new JButton("To this project on github!");
		btnNewButton.setFont(FONT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI(Game.JUNEJURNEYLINK));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("To The Troy Launcher on github!");
		btnNewButton_1.setFont(FONT);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI(Game.THETROYLAUNCHERLINK));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(btnNewButton_1);

		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(false);
	}

	/**
	 * Simple method that shows the window
	 */
	public static void show() {
		if (open == false) {
			open = true;
			frame.setVisible(true);
			if (Game.inGame) {
				Main.game.pauseThreads();
			}

		}
	}

	/**
	 * Simple method that hides the window
	 */
	public static void hide() {
		if (open == true) {
			open = false;
			frame.setVisible(false);
			if (Game.inGame) {
				Main.game.resumeThreads();
			}
			TitleWindow.frame.requestFocus();
		}
	}
}
