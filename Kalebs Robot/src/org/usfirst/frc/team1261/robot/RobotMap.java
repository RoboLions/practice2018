/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1261.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int LEFT_FRONT_DRIVE_PORT = 12;
	public static final int LEFT_BACK_DRIVE_PORT = 3;
	public static final int RIGHT_FRONT_DRIVE_PORT = 15;
	public static final int RIGHT_BACK_DRIVE_PORT = 0;
	public static final int ARM_SEGMENT = 13;
	public static final int BOOM = 2;
	
	public static WPI_TalonSRX leftDriveMotorFront = new WPI_TalonSRX(LEFT_FRONT_DRIVE_PORT);
	public static WPI_TalonSRX leftDriveMotorBack = new WPI_TalonSRX(LEFT_BACK_DRIVE_PORT);
	public static WPI_TalonSRX rightDriveMotorFront = new WPI_TalonSRX(RIGHT_FRONT_DRIVE_PORT);
	public static WPI_TalonSRX rightDriveMotorBack = new WPI_TalonSRX(RIGHT_BACK_DRIVE_PORT);
	public static WPI_TalonSRX Boom = new WPI_TalonSRX(BOOM);
	public static WPI_TalonSRX Arm = new WPI_TalonSRX(ARM_SEGMENT);
	
	
	public static DoubleSolenoid pisto1 = new DoubleSolenoid(10,2,3);// 0 4 5 // 0 0 1
	public static DoubleSolenoid pisto2 = new DoubleSolenoid(10,0,1);// 0 2 1 // 1 1 0
	
	public static DifferentialDrive robotDrive = new DifferentialDrive(leftDriveMotorFront, rightDriveMotorFront);
	public static DifferentialDrive armDrive = new DifferentialDrive(Arm,Arm);
	public static DifferentialDrive boomDrive = new DifferentialDrive(Boom,Boom);
	//public static DoubleSolenoid piston1;
}
