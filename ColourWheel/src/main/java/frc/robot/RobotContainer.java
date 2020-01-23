/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DisplayColor;
import frc.robot.commands.FindColour;
import frc.robot.commands.spinCounter;
import frc.robot.subsystems.ColourWheel;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ColourWheel m_colourWheel = new ColourWheel();
  private final DisplayColor m_autoCommand = new DisplayColor(m_colourWheel);
  private static final String autonomous1="Display Colour", autonomous2="Spin Wheel";
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  // Joystick Control
  Joystick joystick = new Joystick(Constants.OI_DRIVER_CONTROLLER);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    m_chooser.setDefaultOption("Default Auto", autonomous1);
    m_chooser.addOption("Spin Wheel", autonomous2);
    SmartDashboard.putData(m_chooser);

    // Configure the button bindings

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    final JoystickButton greenButton = new JoystickButton(joystick, Constants.OI_BUTTON_G);     
    final JoystickButton yellowButton = new JoystickButton(joystick, Constants.OI_BUTTON_Y);
    final JoystickButton blueButton = new JoystickButton(joystick, Constants.OI_BUTTON_B);
    final JoystickButton redButton = new JoystickButton(joystick, Constants.OI_BUTTON_R);
    final JoystickButton triggerButton = new JoystickButton(joystick, Constants.OI_BUTTON_S);
    
    greenButton.whenPressed(new FindColour(m_colourWheel,"Green"));
    yellowButton.whenPressed(new FindColour(m_colourWheel, "Yellow"));
    blueButton.whenPressed(new FindColour(m_colourWheel, "Blue"));
    redButton.whenPressed(new FindColour(m_colourWheel, "Red"));
    triggerButton.whenPressed(new spinCounter(m_colourWheel));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
