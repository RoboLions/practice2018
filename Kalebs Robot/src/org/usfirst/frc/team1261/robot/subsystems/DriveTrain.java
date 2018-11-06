package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.GoForward;
import org.usfirst.frc.team1261.robot.commands.JoyStickDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**

 *
 */
public class DriveTrain extends Subsystem {
	public static final int TIMEOUT_MS = 10;
	
	WPI_TalonSRX leftMotorFront = RobotMap.leftDriveMotorFront;
	WPI_TalonSRX leftMotorBack = RobotMap.leftDriveMotorBack;
	WPI_TalonSRX rightMotorFront = RobotMap.rightDriveMotorFront;
	WPI_TalonSRX rightMotorBack = RobotMap.rightDriveMotorBack;

	// code borrowed from standard wpi lib library       2/10/2018     M.F.
	DifferentialDrive driveTrain = RobotMap.robotDrive;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public DriveTrain() {
		//leftMotorFront.set(ControlMode.Velocity, 0);
		 //rightMotorFront.set(ControlMode.Velocity, 0);
		 
		 leftMotorFront.setInverted(false);
		 rightMotorFront.setInverted(true);
		 
		 rightMotorBack.setInverted(true);
		 leftMotorBack.setInverted(false);
		
	
		
	}
	
	public void drive(double throttle,double rotate)
	{
		driveTrain.arcadeDrive(rotate, throttle);
	}
	public void setPower(double throttle, double rotate) {
		//System.out.println("its running");
		double left_power = .1;
		double right_power = .1;
		if (rotate<0) {
			right_power = Math.abs(rotate);
			left_power = -Math.abs(rotate);
		}
		if (rotate>0) {
			left_power = rotate;
			right_power = -rotate;
		}
		throttle = .1;
		left_power += throttle;
		right_power += throttle;
		leftMotorFront.set(ControlMode.Velocity,.1);
		rightMotorFront.set(ControlMode.Velocity,.1);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new JoyStickDrive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stop() {
    	driveTrain.stopMotor();
    }
    public void resetEncoders() {
    	leftMotorFront.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
    	rightMotorFront.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
    }
    public int getLeftEncoderPosition() {
    	return leftMotorFront.getSensorCollection().getQuadraturePosition();
    }
    public int getRightEncoderPosition() {
    	return rightMotorFront.getSensorCollection().getQuadraturePosition();
    }
    public double distanceTraveled() {
    	return -(getLeftEncoderPosition() + getRightEncoderPosition())/(2);
    }
    public double rotationTurned() {
    	return (getLeftEncoderPosition() - getRightEncoderPosition())/2;
    }
    public DifferentialDrive getRobotDrive() {
    	return driveTrain;
    }
    public WPI_TalonSRX getLeftMotorFront() {
    	return leftMotorFront;
    }
    public WPI_TalonSRX getLeftMotorBack() {
    	return leftMotorBack;
    }
    public WPI_TalonSRX getRightMotorFront() {
    	return rightMotorFront;
    }
    public WPI_TalonSRX getRightMotorBack() {
    	return rightMotorBack;
    }
    

      
}

