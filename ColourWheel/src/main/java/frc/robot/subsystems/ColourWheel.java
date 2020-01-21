/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;

public class ColourWheel extends SubsystemBase {
  /**
   * Creates a new ColourWheel.
   */
  // Disabled Motor FOR NOW
  // private final WPI_TalonSRX spinner = new WPI_TalonSRX(Constants.CANSPIN);
  private final Talon spinner = new Talon(Constants.PWMSpin);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private Color detectedColor;
  public String motorState="Stopped";
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public ColourWheel() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
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
    motorState = "Clockwise";
  }

  public Color detectedColor() {
    return this.detectedColor;
  }

  public void cclockwise() {
    spinner.set(-0.3);
    motorState = "CounterClockwise";
  }

  public void stop() {
    spinner.set(0);
    motorState = "Stopped";
  }

  public double confidence() {
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    return match.confidence;
  }

  public boolean confident() {
    return (this.confidence()>0.90);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    this.detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    String colorString;
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putString("Spinner Motor", motorState);
  }

public String colourMatch() {
  String colorString;
  Color detectedColor = m_colorSensor.getColor();
  ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
  if (match.color == kBlueTarget) {
    colorString = "Blue";
  } else if (match.color == kRedTarget) {
    colorString = "Red";
  } else if (match.color == kGreenTarget) {
    colorString = "Green";
  } else if (match.color == kYellowTarget) {
    colorString = "Yellow";
  } else {
    colorString = "Unknown";
  }
  return colorString;
}
}
