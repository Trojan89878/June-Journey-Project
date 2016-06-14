package com.troy.junejourney.game.settings;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import com.troy.junejourney.game.*;
import com.troy.junejourney.input.*;

public class SettingsWindow {

	public static boolean open = false;
	private static JFrame frame;

	/**
	 * This method initalises the settings window with all the sliders and check
	 * boxes, however I purposfully make sure that the window isn't visible. I
	 * make it visable when the user presses "o" and hide it when they press "p"
	 */

	public static void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDisplayMode();

		/**
		 * create a new window th include all the sliders and check boxes in.
		 */
		frame = new JFrame();
		frame.setTitle("Game Settings");
		frame.setSize((int) ((float) displayMode.getWidth() / 2f),
				(int) ((float) displayMode.getHeight() / 2f));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JLabel lblLimit = new JLabel("Astroid Difficulty");
		frame.getContentPane().add(lblLimit);

		JSlider slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMaximum(100);
		slider.setMinimum(10);
		frame.getContentPane().add(slider);

		JLabel lblNewLabel = new JLabel("Astroid Difficulty: 50");

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				lblNewLabel.setText("Astroid Difficulty: " + slider.getValue());
				GameSettings.astroidDamageBuffer = slider.getValue();
			}
		});

		frame.getContentPane().add(lblNewLabel);

		Component verticalStrut = Box.createVerticalStrut(20);
		frame.getContentPane().add(verticalStrut);

		JLabel lblCollisionDetectionPoint = new JLabel("Collision Detection Point Count");
		frame.getContentPane().add(lblCollisionDetectionPoint);

		JSlider slider_1 = new JSlider();
		slider_1.setPaintTicks(true);
		slider_1.setMinimum(20);
		slider_1.setValue(40);
		JLabel lblCollisionDetectionPoints = new JLabel("Collision Detection Points: 40");

		slider_1.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				lblCollisionDetectionPoints
						.setText("Collision Detection Points: " + slider_1.getValue());
				GameSettings.circlePointCount = slider_1.getValue();
			}
		});

		frame.getContentPane().add(slider_1);

		frame.getContentPane().add(lblCollisionDetectionPoints);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		frame.getContentPane().add(verticalStrut_1);

		JLabel lblShowHitBoxes = new JLabel("Show Hit Boxes");
		frame.getContentPane().add(lblShowHitBoxes);

		JCheckBox checkBox_1 = new JCheckBox("");
		frame.getContentPane().add(checkBox_1);

		checkBox_1.setSelected(GameSettings.showHitBoxes);

		checkBox_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameSettings.showHitBoxes = checkBox_1.isSelected();
			}
		});

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				hide();
			}
		});

		frame.addKeyListener(new KeyInput());
		frame.requestFocus();
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
		frame.requestFocus();
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
		}
		if (Game.inGame) {
			Game.jframe.requestFocus();
		}
	}
}
