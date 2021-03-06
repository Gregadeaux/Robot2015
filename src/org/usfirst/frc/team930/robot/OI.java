package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	private static final double DEADBAND = .1;
	public final static int DRIVER_PORT = 0;
	public final static int CODRIVER_PORT = 1;

	Joystick driverXbox = new Joystick(DRIVER_PORT);
	Joystick coDriverXbox = new Joystick(CODRIVER_PORT);
	
	JoystickButton aButton = new JoystickButton(driverXbox, 1);
	JoystickButton xButton = new JoystickButton(driverXbox, 3);
	JoystickButton bButton = new JoystickButton(driverXbox, 2);
	JoystickButton yButton = new JoystickButton(driverXbox, 4);

	public static OI getInstance() {
		return Holder.instance;
	}
	
	private OI (){
		aButton.whenPressed(new CloseLeftClaw());
		xButton.whenPressed(new OpenLeftClaw());
		bButton.whenPressed(new CloseRightClaw());
		yButton.whenPressed(new OpenRightClaw());
	}

	public static class Holder {
		public static final OI instance = new OI();
	}

	public double getStrafe() {
		double axis = driverXbox.getRawAxis(0);
		return Math.abs(axis) < DEADBAND ? 0 : axis;
//		if(Math.abs(axis) < DEADBAND) {
//			return 0;
//		}
//		return axis;
	}
	
	public double getForward() {
		double axis = driverXbox.getRawAxis(1);
		return Math.abs(axis) < DEADBAND ? 0 : -axis;
//		if(Math.abs(axis) < DEADBAND) {
//			return 0;
//		}
//		return -1 * axis;
	}
	
	public double getRotX() {
		double axis = driverXbox.getRawAxis(4);
		return Math.abs(axis) < DEADBAND ? 0 : axis;
//		if(Math.abs(axis) < DEADBAND) {
//			return 0;
//		}
//		return axis;
	}
	
	public double getRotY() {
		double axis = driverXbox.getRawAxis(5);
		return Math.abs(axis) < DEADBAND ? 0 : axis;
//		if(Math.abs(axis) < DEADBAND) {
//			return 0;
//		}
//		return axis;
	}
}
