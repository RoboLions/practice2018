package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoyStickDrive extends Command {
public static final Joystick JOYSTICK = OI.getDriverJoystick();
//public static final Trigger TRIGGER = OI.getDriverTrigger();
public static final int THROTTLE = OI.AXIS_LEFT_STICK_Y;
public static final int ROTATE = OI.AXIS_RIGHT_STICK_X;
public static GoForward goForward = new GoForward();
public static Turn turn = new Turn(1);
//public static final int TRIGGER = OI.AXIS_LEFT_TRIGGER;
    public JoyStickDrive() {
    	requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (JOYSTICK.getRawButtonPressed(2)) {
    		goForward.start();
    		
    	}
    	if (JOYSTICK.getRawButtonPressed(5)) {
    		turn.start();
    		
    	}
    	if (JOYSTICK.getRawButton(3)) {
    	if (Robot.Paused)	{
    	Robot.Paused = false;
    	} else {
        	Robot.Paused = true;

    	}
    		
    	
    	}
    	
    	double throttle = -JOYSTICK.getY();
    	double rotate = JOYSTICK.getX();
    	
    	//Robot.driveTrain.getRobotDrive().
    	Robot.driveTrain.drive(throttle*(-JOYSTICK.getZ()+1)/2, rotate*(-JOYSTICK.getZ()+1)/2);
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
