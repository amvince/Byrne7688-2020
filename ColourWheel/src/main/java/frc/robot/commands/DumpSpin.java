/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DumpTruck;

public class DumpSpin extends CommandBase {
  /**
   * Creates a new DumpSpin.
   */
  private double speed;
  private DumpTruck dumptruck;
  public DumpSpin(DumpTruck dumptruck, double speed ) {
    // Use addRequirements() here to declare subsystem dependencies.
   this.dumptruck = dumptruck;
   this.speed = speed;
   addRequirements(dumptruck); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    dumptruck.spinBelt(this.speed);
    System.out.println("Dumptruck has started to spin");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Dumptruck is spinning");
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.speed=0.0;
    dumptruck.stop();
    System.out.println("Dumptruck has stopped");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
