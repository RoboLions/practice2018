package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManipPid extends Command {
	
	public static final double POWER = 1;

	

	public static final double BOOM_GAIN = .01;
	public static final double YAW_INTEGRAL = 0;
	public static final double ARM_GAIN = .01;
	public static final double POS_INTEGRAL = 0.0000; //.05;
	public static final double POS_DERIVATIVE = 0;
	double boom_cmd = 500; //3 meters is goals
	double arm_cmd = 250;
	double boom_int_term = 0;
	double arm_int_term = 0;
	double armTh = POWER;	// th is a velocity commandveled();

	double boomTh = POWER;

    public ManipPid(double boom_amo, double arm_amo) {
    	boom_cmd = boom_amo;
    	arm_cmd = arm_amo;
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.manipulator);
    	setTimeout(3.0);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double BoomEnc = -(Robot.manipulator.getVertEncoder());
    	double ArmEnc = (Robot.manipulator.getBoomEncoder());
    
    	
    	double boom_err = boom_cmd - BoomEnc;
    	double arm_err = arm_cmd - ArmEnc;    	
    	

    		// th is a velocity commandveled();
    	//System.out.println(enc_pos);
    	//System.out.println(Robot.driveTrain.getLeftEncoderPosition() - Robot.driveTrain.getRightEncoderPosition());
    	
    	//double enc_vel = Robot.driveTrain.VelocityinMeters();
    	/*
    	if (pos_err > 10) {
    		pos_int_term += boom_err;
    	}
    	if (boom_err < -10) { //no integral gain within 2 cm
    		pos_int_term += boom_err;
    	
    	}
    	if (boom_err > 10 && boom_err<-10) { //no integral gain within 2 cm
    		pos_int_term = 0;
    	}
    	if (boom_err > 100) {
    		pos_int_term = 0;
    	}
    	if (boom_err < -100) {
    		pos_int_term = 0;
    	}
    	*/
    	boomTh = (boom_err*BOOM_GAIN)*POWER; 
    	armTh = (arm_err*ARM_GAIN)*POWER; //enc_vel*POS_DERIVATIVE;
    	
    	if (boomTh >.7) {
    		boomTh = .7;
    	}
    	if (boomTh <-.7) {
    		boomTh = -.7;
    	}
    	
    	if (armTh >.7) {
    		armTh = .7;
    	}
    	if (armTh <-.7) {
    		armTh = -.7;
    	}
    
    	System.out.println(boom_err);

    	//posn_err = pos_cmd-enc_posn
    	//th = posn_err*kp + posn_err_int*ki - enc_vel*kd
    	//double ro = 0;
    	
    	//System.out.println(-delta_v);
    	//delta_v = 0;
    	Robot.manipulator.setPower(-armTh,boomTh);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Math.abs(armTh)<.5)&&(Math.abs(boomTh)<.5);

    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
