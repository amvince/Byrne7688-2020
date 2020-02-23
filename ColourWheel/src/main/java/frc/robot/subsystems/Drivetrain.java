/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private static WPI_VictorSPX left_f;
  private static WPI_VictorSPX left_r;
  private static WPI_VictorSPX right_f;
  private static WPI_VictorSPX right_r;

  private static SpeedControllerGroup m_right;
  private static SpeedControllerGroup m_left;
  private static DifferentialDrive m_drive;

  private double top_speed = Constants.TOP_SPEED;
  private double turn_speed = Constants.TURN_SPEED;

  public Drivetrain() {
    left_f = new WPI_VictorSPX(Constants.DRIVETRAIN_LEFT_FRONT);
    left_r = new WPI_VictorSPX(Constants.DRIVETRAIN_LEFT_REAR);
    right_f = new WPI_VictorSPX(Constants.DRIVETRAIN_RIGHT_FRONT);
    right_r = new WPI_VictorSPX(Constants.DRIVETRAIN_RIGHT_REAR);
    
    m_left = new SpeedControllerGroup(left_f, left_r);
    m_right = new SpeedControllerGroup(right_f, right_r);
    m_drive = new DifferentialDrive(m_left, m_right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double speed, double direction) {
    m_drive.arcadeDrive(speed * top_speed, direction * turn_speed);
  }
  public void stop() {
    m_drive.tankDrive(0,0);
  }
}
