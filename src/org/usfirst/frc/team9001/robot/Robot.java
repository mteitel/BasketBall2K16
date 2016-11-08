

package org.usfirst.frc.team9001.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Victor 
		rightDriveA, rightDriveB,
		leftDriveA, leftDriveB;
	
	Talon ballLoader;
	CANTalon flywheel;
	
	Joystick xbox;
	
	double shooterPwr;
	boolean lastBtnPressed, btnPressed;
	boolean runFeeder;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	rightDriveA = new Victor(0); // positive is forward
    	rightDriveB = new Victor(1); // positive is backward
    	leftDriveA = new Victor(4); // positive is backward
    	leftDriveB = new Victor(5); // positive is forward
    	
    	ballLoader = new Talon(2);
    	flywheel = new CANTalon(2);
    	
    	xbox = new Joystick(0);
    	
    	shooterPwr = 0;
    	lastBtnPressed = btnPressed = false;
    	runFeeder = false;
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	btnPressed = xbox.getRawButton(1);
    	
    	if (btnPressed && !lastBtnPressed){
    		runFeeder = !runFeeder;	
    	}
    	
    	
    	if (runFeeder){
    		flywheel.set(.5);
    		ballLoader.set(1);
    	}else{
    		flywheel.set(0);
    		ballLoader.set(0);
    	}
    	
    	double left;
    	double right;
    	
    	right = -.6*(xbox.getRawAxis(1)+xbox.getRawAxis(4));
    	left = -.6*(xbox.getRawAxis(1)-xbox.getRawAxis(4));
    	
    	rightDriveA.set(right);
    	rightDriveB.set(right);
    	leftDriveA.set(-left);
    	leftDriveB.set(left);
    	
    	
    	lastBtnPressed = btnPressed;
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    	btnPressed = xbox.getRawButton(1);
    	
    	if (btnPressed && !lastBtnPressed){
    		runFeeder = !runFeeder;	
    	}
    	
    	
    	if (runFeeder){
    		flywheel.set(-1);
    	}else{
    		flywheel.set(0);
    	}
    	rightDriveA.set(xbox.getRawAxis(0));
    	rightDriveB.set(xbox.getRawAxis(1));
    	leftDriveA.set(xbox.getRawAxis(2));
    	leftDriveB.set(xbox.getRawAxis(3));
    	ballLoader.set(-1 * xbox.getRawAxis(5));
    	
    	lastBtnPressed = btnPressed;
    }
    
}
