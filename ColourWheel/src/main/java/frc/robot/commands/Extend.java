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
import frc.robot.subsystems.DumpTruck;

public class Extend extends CommandBase {
  /**
   * Creates a new Extend.
   */
  private DumpTruck dumptruck;
  private final NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private final NetworkTable table = inst.getTable("SmartDashboard");

  public Extend(DumpTruck dumptruck) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.dumptruck = dumptruck;
    addRequirements(dumptruck);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    dumptruck.extend();
    System.out.println("Lifting Duptruck");
    table.getEntry("dumpTruck").setBoolean(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Dumptruck is lifted");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    dumptruck.retract();
    System.out.println("Retracting Dumptruck");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
