/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DumpTruck extends SubsystemBase {
  /**
   * Creates a new dumpTruck.
   */

  // Solenoid (Dual Solenoid)
  private static DoubleSolenoid ds = new DoubleSolenoid(Constants.D_SOLENOID_F, Constants.D_SOLENOID_R);
  // private static final Compressor c = new Compressor(Constants.PN_COMPRESSOR);
  
  // Two Speed Controllers
  private static WPI_TalonSRX belt_a;
  // private static WPI_TalonSRX belt_b;

  public DumpTruck() {
    belt_a = new WPI_TalonSRX(Constants.BELT_A);
    // belt_b = new WPI_TalonSRX(Constants.BELT_B);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void spinBelt(double speed) {
    belt_a.set(speed * Constants.BELT_MAX);
    // belt_b.set(speed * Constants.BELT_MAX);
  }

  public void stop() {
    belt_a.set(0.0);
    // belt_b.set(0.0);
  }

  public void extend() {
    // code to extend pistons
    ds.set(DoubleSolenoid.Value.kForward);
    // s.set(Solenoid.Value.kReverse);
  }
  public void retract() {
    // code to retract piston
    ds.set(DoubleSolenoid.Value.kReverse);
    // s.set(Solenoid.Value.kForward);
  }
}
