/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private final WPI_TalonSRX m_rightF, m_rightR, m_leftF, m_leftR;
  private final SpeedControllerGroup right, left;
  private final DifferentialDrive m_drive;

  private final Encoder leftEnc, rightEnc;

  public Drivetrain() {

    m_rightF = new WPI_TalonSRX(4);
    m_rightR = new WPI_TalonSRX(3);
    m_leftF = new WPI_TalonSRX(1);
    m_leftR = new WPI_TalonSRX(2);
    left = new SpeedControllerGroup(m_leftF, m_leftR);
    right = new SpeedControllerGroup(m_rightF, m_rightR);

    m_drive = new DifferentialDrive(left, right);

    rightEnc = new Encoder(1, 2, 3);
    leftEnc = new Encoder(4, 5, 6, true);

    rightEnc.setDistancePerPulse(4/256);
    leftEnc.setDistancePerPulse(4/256);

  }

  public void ArcadeDrive(double speed, double dir) {
    m_drive.arcadeDrive(speed, dir);
    System.out.println("Speed: "+speed);
    // System.out.println("Distance: "+getDistance());
  }

  public void tankDrive(double lSpeed, double rSpeed) {
    m_drive.tankDrive(lSpeed, rSpeed);
  }

  public void stop() {
    m_drive.tankDrive(0,0);
  }
  public void reset() {
    rightEnc.reset();
    leftEnc.reset();
  }

  public double getOffset(){
    double offSet = rightEnc.getDistance() - leftEnc.getDistance();
    System.out.println("Offset: "+offSet);
    return offSet;
  }

  public double getDistance() {
    double distance = (rightEnc.getDistance() + leftEnc.getDistance())/2;
    System.out.println("Distance: "+distance);
    return(distance);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
