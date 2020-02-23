/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColourWheel;

public class spinFunction extends CommandBase {
  /**
   * Creates a new spinFunction.
   */
  private final ColourWheel m_wheel;
  private final NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private final NetworkTable table = inst.getTable("SmartDashboard");
  
  public spinFunction(ColourWheel wheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_wheel = wheel;
    addRequirements(wheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Read network table for wheel
    // conditional command based on network table command.
    String cmd = table.getEntry("wheel_function").getString("spin");
    switch(cmd) {
      case "red":
        new FindColour(this.m_wheel, "red");
        break;
      case "blue":
        new FindColour(this.m_wheel, "blue");
        break;
      case "green":
        new FindColour(this.m_wheel, "green");
        break;
      case "yellow":
        new FindColour(this.m_wheel, "yellow");
        break;
      case "spin":
      default:
        new spinCounter(this.m_wheel);
        break;
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
