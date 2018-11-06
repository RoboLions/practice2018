package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;
import org.usfirst.frc.team1261.robot.subsystems.Manipulator;
import org.usfirst.frc.team1261.robot.subsystems.Pneumatics;
import org.usfirst.frc.team1261.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmC extends Command {
	public static final Joystick JOYSTICK = OI.getManipulatorJoystick();
	//public static final Trigger TRIGGER = OI.getDriverTrigger();
	public static final int THROTTLE = OI.AXIS_LEFT_STICK_Y;
	public static final int ROTATE = OI.AXIS_RIGHT_STICK_X;
	public static boolean Open;
	public static ManipPid Pickmanipid = new ManipPid(450,250);
	public static ManipPid Homemanipid = new ManipPid(0,0);
	public static ManipPid Outmanipid = new ManipPid(200,700);
	public static ManipPid Backmanipid = new ManipPid(-50,800);

    public ArmC() {
    	requires(Robot.manipulator);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Open = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	double throttle = -JOYSTICK.getY();
    	if (Math.abs(throttle)<.2) {
    		throttle = 0;
    	}
    	double rotate = JOYSTICK.getX();
    	if (Math.abs(rotate)<.15) {
    		rotate = 0;
    	}
    	if (JOYSTICK.getRawButtonPressed(4) == true) {
    		Backmanipid.start();
    	}
    	if (JOYSTICK.getRawButtonPressed(2) == true) {
    		Pickmanipid.start();
    	}
    	if (JOYSTICK.getRawButtonPressed(3) == true) {
    		Homemanipid.start();
    	}
    	if (JOYSTICK.getRawButtonPressed(5) == true) {
    		Outmanipid.start();
    	}
    	Robot.manipulator.setPower(rotate,throttle);
    	

    	if (JOYSTICK.getRawButtonPressed(1)) {
    		System.out.println(RobotMap.pisto1.get());
    		
			if(RobotMap.pisto1.get() == DoubleSolenoid.Value.kForward) {
				Robot.pneumatics.pistonIn();
    		}else {
				Robot.pneumatics.pistonOut(); 
    		}
    	}
    }

    	
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
