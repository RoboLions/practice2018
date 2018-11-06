package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoForward extends Command {
	
	public static final double POWER = 1;
	public static final int MOTOR_ENCODER_TICKS_PER_REV = 4096;
	public static final double WHEEL_CIRCUMFERENCE = 54;
	public static final double DISTANCE = 2; //in feet
	public static final double REVOLUTIONS = (DISTANCE/WHEEL_CIRCUMFERENCE);
	public static final double VOLTAGE_RAMP_RATE = 24.0;

	

	public static final double YAW_GAIN = .001;
	public static final double YAW_INTEGRAL = 0;
	public static final double POS_GAIN = .0045;
	public static final double POS_INTEGRAL = 0.0000; //.05;
	public static final double POS_DERIVATIVE = 0;
	double pos_cmd = 500; //3 meters is goals
	double head_cmd = 0;
	double yaw_int_term = 0;
	double pos_int_term = 0;
	
	double target_yaw = head_cmd; //is not final because if it was we cannot change it.
	
    public GoForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setTimeout(10.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
    	Robot.driveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	double leftEnc = (Robot.driveTrain.getLeftEncoderPosition());
    	double RightEnc = (Robot.driveTrain.getRightEncoderPosition())*1.03;
    	double current_yaw = (-leftEnc)-RightEnc;
    	
    	double yaw_error = target_yaw - current_yaw;
    	// do not change
    	if (yaw_error > 0.1) {
    		yaw_int_term += yaw_error;
    	}
    	if (yaw_error < -0.1) {
    		yaw_int_term += yaw_error;
    	}
    	if (yaw_error > 15.0) {
    		yaw_int_term = 0;
    	}
    	if (yaw_error < -15.0) {
    		yaw_int_term = 0;
    	}
    	
    	
    	double delta_v = yaw_error * YAW_GAIN + yaw_int_term * YAW_INTEGRAL;

    	double th = POWER;	// th is a velocity command
    	double enc_pos = Robot.driveTrain.distanceTraveled();
    	//System.out.println(enc_pos);
    	//System.out.println(Robot.driveTrain.getLeftEncoderPosition() - Robot.driveTrain.getRightEncoderPosition());
    	double pos_err = pos_cmd - enc_pos;
    	//System.out.println(yaw_error);
    	
    	//double enc_vel = Robot.driveTrain.VelocityinMeters();
    	if (pos_err > 10) {
    		pos_int_term += pos_err;
    	}
    	if (pos_err < -10) { //no integral gain within 2 cm
    		pos_int_term += pos_err;
    	
    	}
    	if (pos_err > 10 && pos_err<-10) { //no integral gain within 2 cm
    		pos_int_term = 0;
    	}
    	if (pos_err > 100) {
    		pos_int_term = 0;
    	}
    	if (pos_err < -100) {
    		pos_int_term = 0;
    	}
    	th = (pos_err*POS_GAIN + pos_int_term*POS_INTEGRAL)*POWER;  //enc_vel*POS_DERIVATIVE;
    	
    	if (th >.75) {
    		th = .75;
    	}
    	if (delta_v >.3) {
    		delta_v = .3;
    	}
    	if (delta_v <-.3) {
    		delta_v = -.3;
    	}
    	System.out.println(pos_err);

    	//posn_err = pos_cmd-enc_posn
    	//th = posn_err*kp + posn_err_int*ki - enc_vel*kd
    	double ro = 0;
    	
    	//System.out.println(-delta_v);
    	//delta_v = 0;
    	Robot.driveTrain.drive(th, delta_v);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    	//System.out.println(Robot.driveTrain.distanceTraveled()+" > "+(MOTOR_ENCODER_TICKS_PER_REV * REVOLUTIONS));
    	
    	//return -Robot.driveTrain.distanceTraveled() >= (MOTOR_ENCODER_TICKS_PER_REV * REVOLUTIONS);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
