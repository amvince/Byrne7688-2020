/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColourWheel;

public class FindColour extends CommandBase {
  /**
   * Creates a new DisplayColor.
   */
  private final ColourWheel m_wheel;
  private String s_target="None", s_current;
  private boolean foundColour;

  public FindColour(ColourWheel wheel, String target) {
    s_target = target;
    m_wheel = wheel;
    SmartDashboard.putString("Looking For:", s_target);
    System.out.print("Looking For: ");
    System.out.println(s_target);
    addRequirements(m_wheel);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      s_current = m_wheel.colourMatch();
      foundColour = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      s_current = m_wheel.colourMatch();
      SmartDashboard.putString("Currently On:", s_current);
      System.out.println("Looking...");
      System.out.print("Currently On: ");
      System.out.println(s_current);
      if (s_current == s_target) {
        foundColour = true;
      } else {
        m_wheel.clockwise();
      }
    
  } 
    // if current != target start spin the wheel.
    // Remember, there is a 90 degree phase shift
    // If target is reached, set a flag for the "isFinished"
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Stopping Motor");
    m_wheel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (foundColour) {
      m_wheel.stop();
      System.out.print("Found ");
      System.out.println(s_target);
      return true;
    }
    return false;
  }
}
