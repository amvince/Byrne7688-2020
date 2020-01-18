/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColourWheel;

public class spinCounter extends CommandBase {
  /**
   * Creates a new spinCounter.
   */
  private final ColourWheel m_wheel = new ColourWheel();
  // private Color initColour, prevColour;
  private String s_initColour, s_prevColour;

  
  private double counter;

  public spinCounter() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /* 
    initColour = m_wheel.detectedColor();
    prevColour = initColour;
    */
    counter = 0;
    s_initColour = m_wheel.colourMatch();
    s_prevColour = s_initColour;
    SmartDashboard.putString("Starting Colour", s_initColour);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_wheel.clockwise();
    // This uses the "numbers" so it won't be perfect. Needs to be set for "matched" name instead.
    //counter += ((m_wheel.detectedColor()==initColour) && (m_wheel.detectedColor() != prevColour)) ? 0.5 : 0;

    /* This might be the code for a "named" colour counter. */
    String currColour = m_wheel.colourMatch();
    counter += ((currColour == s_initColour) && (currColour != s_prevColour)) ? 0.5 : 0;
    SmartDashboard.putString("Detected Color", currColour);
    SmartDashboard.putString("Spins", String.valueOf(counter/2));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_wheel.stop();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (counter == 3);
  }
}
