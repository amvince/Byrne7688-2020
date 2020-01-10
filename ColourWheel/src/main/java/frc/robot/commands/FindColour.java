/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColourWheel;

public class FindColour extends CommandBase {
  /**
   * Creates a new DisplayColor.
   */
  private ColourWheel m_wheel;
  private Color m_target, m_current, m_previous;
  private boolean colorMatch;

  public FindColour(ColourWheel wheel, Color target) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_wheel = wheel;
    m_target = target;
    addRequirements(wheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_previous=m_wheel.detectedColor();
    colorMatch = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_current = m_wheel.detectedColor();
    if (m_current == m_target) {  // Detecting EXACT match, not shifted match
      colorMatch = true;
    } else {
      m_wheel.clockwise();
    }
    // if current != target start spin the wheel.
    // Remember, there is a 90 degree phase shift
    // If target is reached, set a flag for the "isFinished"
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (colorMatch) {
      m_wheel.stop();
      return true;
    }
    return false;
  }
}
