package org.usfirst.frc.team1261.robot.subsystems;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.RobotMap;
import org.usfirst.frc.team1261.robot.commands.ArmC;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Manipulator extends Subsystem {
	public static final double VERT_GAIN_F = 1; //1
	public static final double VERT_GAIN_P = 5; // 1.2 //10
	private static final int TIMEOUT_MS = 10;

	//public static final double VERT_GAIN_F = 1;
	//public static final double VERT_GAIN_P = 8; // 1.2
	public static final double VERT_GAIN_I = 0;	
	public static final double VERT_GAIN_D = 0;
	public static final double VERT_RAMPRATE = 1;
	public static final int BOOM_PIDF_PROFILE = 0;
	public static final double BOOM_GAIN_F = 1;
	public static final double BOOM_GAIN_P = 5; // 1.2
	public static final double BOOM_GAIN_I = 0;	
	public static final double BOOM_GAIN_D = 0;
	public static final double BOOM_RAMPRATE = 1;//0.5;

	public static WPI_TalonSRX arm = RobotMap.Arm;
	public static WPI_TalonSRX boom = RobotMap.Boom;
	
	DifferentialDrive ArmTrain = RobotMap.armDrive;
	DifferentialDrive BoomTrain = RobotMap.boomDrive;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Manipulator() {
		arm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		boom.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		
		arm.set(ControlMode.Position, 0);
		boom.set(ControlMode.Position, 0);
		
		arm.setSensorPhase(true);
		boom.setSensorPhase(false);
		
		 //vertMotor.setInverted(false);
		 //boomMotor.setInverted(true);
		 
		arm.configNominalOutputForward(0,TIMEOUT_MS);
		arm.configNominalOutputReverse(0,TIMEOUT_MS);
		arm.configPeakOutputForward(1,TIMEOUT_MS);
		arm.configPeakOutputReverse(-1,TIMEOUT_MS);
		 
		boom.configNominalOutputForward(0,TIMEOUT_MS);
		boom.configNominalOutputReverse(0,TIMEOUT_MS);
		boom.configPeakOutputForward(1,TIMEOUT_MS);
		boom.configPeakOutputReverse(-1,TIMEOUT_MS);
		 
		 arm.configNeutralDeadband(0.001, TIMEOUT_MS);
		 boom.configNeutralDeadband(0.001, TIMEOUT_MS);

		 arm.config_kF(0,VERT_GAIN_F,TIMEOUT_MS);
		 arm.config_kP(0,VERT_GAIN_P,TIMEOUT_MS);
		 arm.config_kI(0,VERT_GAIN_I,TIMEOUT_MS);
		 arm.config_kD(0,VERT_GAIN_D,TIMEOUT_MS);
		 arm.configClosedloopRamp(VERT_RAMPRATE, TIMEOUT_MS);
		 
		 boom.config_kF(0,BOOM_GAIN_F,TIMEOUT_MS);
		 boom.config_kP(0,BOOM_GAIN_P,TIMEOUT_MS);
		 boom.config_kI(0,BOOM_GAIN_I,TIMEOUT_MS);
		 boom.config_kD(0,BOOM_GAIN_D,TIMEOUT_MS);
		 boom.configClosedloopRamp(BOOM_RAMPRATE, TIMEOUT_MS);
		
		
	}
    public void initDefaultCommand() {
    	setDefaultCommand(new ArmC());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static double getBoomEncoder() {
    	return(boom.getSensorCollection().getQuadraturePosition());
    }
    
    public static double getVertEncoder() {
    	return(arm.getSensorCollection().getQuadraturePosition());
    }
    
    public void setPower(double boomV ,double armV) {
		//double left_power = .1;
		//double right_power = .1;
		
		ArmTrain.arcadeDrive(armV, 0);
		BoomTrain.arcadeDrive(boomV, 0);
		//boom.set(ControlMode.Velocity,boomV);
		
	}
}

