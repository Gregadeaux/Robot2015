package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive.Outputs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {

	// By Noah and Nick

	final int CODES_PER_REV = 414;
	public final double DEG_TO_GEAR_TO_REV = 1 / 360.0;
	final int FRONT_LEFT = 2;
	final int FRONT_RIGHT = 1;
	final int BACK_LEFT = 4;
	final int BACK_RIGHT = 3;

	public SwerveDrive swerve;

	public CANTalon frDrive;
	public CANTalon flDrive;
	public CANTalon blDrive;
	public CANTalon brDrive;

	public CANJaguar frRot;
	public CANJaguar flRot;
	public CANJaguar blRot;
	public CANJaguar brRot;

	public Drivetrain(double length, double width) {
		this.swerve = new SwerveDrive(length, width);
		this.setMotors();
	}

	public Drivetrain(double length, double width, boolean fieldcent) {
		this.swerve = new SwerveDrive(length, width, fieldcent);
		this.setMotors();
	}

	public void setMotors() {

		frDrive = new CANTalon(FRONT_RIGHT);
		flDrive = new CANTalon(FRONT_LEFT);
		blDrive = new CANTalon(BACK_LEFT);
		brDrive = new CANTalon(BACK_RIGHT);

		frRot = new CANJaguar(FRONT_RIGHT);
		flRot = new CANJaguar(FRONT_LEFT);
		blRot = new CANJaguar(BACK_LEFT);
		brRot = new CANJaguar(BACK_RIGHT);

		frRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, -5900,
				-80, 0);
		flRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, -5900,
				-80, 0);
		blRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, -5900,
				-80, 0);
		brRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, -5900,
				-80, 0);

		frRot.enableControl();
		flRot.enableControl();
		blRot.enableControl();
		brRot.enableControl();
	}

	public void drive(double forward, double strafe, double rot) {
		swerve.updateSwerve(forward, strafe, rot);

		frDrive.set(swerve.output(Outputs.frontRightSpeed));
		flDrive.set(swerve.output(Outputs.frontLeftSpeed));
		blDrive.set(swerve.output(Outputs.backLeftSpeed));
		brDrive.set(swerve.output(Outputs.backRightSpeed));

		frRot.set(swerve.output(Outputs.frontRightAngle) * DEG_TO_GEAR_TO_REV);
		flRot.set(swerve.output(Outputs.frontLeftAngle) * DEG_TO_GEAR_TO_REV);
		blRot.set(swerve.output(Outputs.backLeftAngle) * DEG_TO_GEAR_TO_REV);
		brRot.set(swerve.output(Outputs.backRightAngle) * DEG_TO_GEAR_TO_REV);

		// outputings
		/*
		 * SmartDashboard.putNumber("FRS",
		 * swerve.output(Outputs.frontRightSpeed));
		 * SmartDashboard.putNumber("FLS",
		 * swerve.output(Outputs.frontLeftSpeed));
		 * SmartDashboard.putNumber("BLS",
		 * swerve.output(Outputs.backLeftSpeed));
		 * SmartDashboard.putNumber("BRS",
		 * swerve.output(Outputs.backRightSpeed));
		 * 
		 * SmartDashboard.putNumber("FRA",
		 * swerve.output(Outputs.frontRightAngle));
		 * SmartDashboard.putNumber("FLA",
		 * swerve.output(Outputs.frontLeftAngle));
		 * SmartDashboard.putNumber("BLA",
		 * swerve.output(Outputs.backLeftAngle));
		 * SmartDashboard.putNumber("BRA",
		 * swerve.output(Outputs.backRightAngle));
		 */

		// thigns
		System.out.println("FRS " + swerve.output(Outputs.frontRightSpeed));
		System.out.println("FLS " + swerve.output(Outputs.frontLeftSpeed));
		System.out.println("BLS " + swerve.output(Outputs.backLeftSpeed));
		System.out.println("BRS " + swerve.output(Outputs.backRightSpeed));

		System.out.println("FRA " + swerve.output(Outputs.frontRightAngle));
		System.out.println("FLA " + swerve.output(Outputs.frontLeftAngle));
		System.out.println("BLA " + swerve.output(Outputs.backLeftAngle));
		System.out.println("BRA " + swerve.output(Outputs.backRightAngle));
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());

	}
}