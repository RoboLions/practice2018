/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1261.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static final int DRIVER_JOYSTICK = 1;
	public static final int MANIPULATOR_JOYSTICK = 2;
	
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int BUTTON_LEFT_BUMPER = 5;
	public static final int BUTTON_RIGHT_BUMPER = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;
	public static final int BUTTON_LEFT_STICK = 9;
	public static final int BUTTON_RIGHT_STICK = 10;

	public static final int AXIS_LEFT_STICK_X = 0;
	public static final int AXIS_LEFT_STICK_Y = 1;
	public static final int AXIS_LEFT_TRIGGER = 2;
	public static final int AXIS_RIGHT_TRIGGER = 3;
	public static final int AXIS_RIGHT_STICK_X = 4;
	public static final int AXIS_RIGHT_STICK_Y = 5;
	
	//static Trigger driverTrigger = new trigger(DRIVER_JOYSTICK);

	static Joystick driverJoystick = new Joystick(DRIVER_JOYSTICK);
	
	static Joystick manipulatorJoystick = new Joystick(MANIPULATOR_JOYSTICK);
	
	
	public static Joystick getDriverJoystick() {
		return driverJoystick;
	}
	public static Joystick getTriggerJoystick() {
		return driverJoystick;
	}
	public static Joystick getManipulatorJoystick() {
		return manipulatorJoystick;
	}
}
