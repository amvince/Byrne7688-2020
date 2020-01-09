/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;

public class ColourWheel extends SubsystemBase {
  /**
   * Creates a new ColourWheel.
   */
  private final WPI_TalonSRX spinner = new WPI_TalonSRX(Constants.CANSPIN);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private Color detectedColor;

  public ColourWheel() {

  }

  public double red() {
    return detectedColor.red;
  }

  public double green() {
    return detectedColor.green;
  }

  public double blue() {
    return detectedColor.blue;
  }

  public void clockwise() {
    spinner.set(0.3);
  }

  public void cclockwise() {
    spinner.set(-0.3);
  }

  public void stop() {
    spinner.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    detectedColor = m_colorSensor.getColor();
  }
}
