/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class autoDrive extends CommandBase {
  /**
   * Creates a new autoDrive.
   */
  private Drivetrain m_drive;
  private double distance, desiredDistance;

  public autoDrive(Drivetrain drive, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_drive = drive;
    this.desiredDistance = distance;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.reset();
    distance=0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distance = m_drive.getDistance();
    double offset = m_drive.getOffset();
    m_drive.ArcadeDrive(0.4, offset);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (distance>=desiredDistance);
  }
}
