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
import frc.robot.commands.DumpSpin;
import frc.robot.commands.Extend;
import frc.robot.commands.FindColour;
import frc.robot.commands.Retract;
import frc.robot.commands.spinCounter;
import frc.robot.commands.spinFunction;
import frc.robot.subsystems.ColourWheel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DumpTruck;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ColourWheel m_colourWheel = new ColourWheel();
  private static final Drivetrain drivetrain = new Drivetrain();
  private static final DumpTruck dumptruck = new DumpTruck();
  
  private Command m_autoCommand; // = new DisplayColor(m_colourWheel);
  private static final String autonomous1="Display Colour", autonomous2="Spin Wheel";
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  


  // Joystick Control
  Joystick joystick = new Joystick(Constants.OI_DRIVER_CONTROLLER);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    

    m_chooser.setDefaultOption("Display Colour Value", autonomous1);
    m_chooser.addOption("Spin Wheel", autonomous2);
    SmartDashboard.putData(m_chooser);

    drivetrain.arcadeDrive(joystick.getRawAxis(Constants.OI_STICK_Y),
                joystick.getRawAxis(Constants.OI_STICK_X));
    
    
    
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
    final JoystickButton triggerButton = new JoystickButton(joystick, Constants.OI_TRIGGER);
    final JoystickButton dExtend = new JoystickButton(joystick, Constants.OI_BUTTON_5);
    final JoystickButton dRetract = new JoystickButton(joystick, Constants.OI_BUTTON_6);
    final JoystickButton dSpinForward = new JoystickButton(joystick, Constants.OI_BUTTON_3);
    final JoystickButton dSpinReverse = new JoystickButton (joystick, Constants.OI_BUTTON_4);
    // add joystick button to extend/retract dumptruck
    // add joystick button to forward/reverse dumptruck motor

    /*
    greenButton.whenPressed(new FindColour(m_colourWheel,"Green"));
    yellowButton.whenPressed(new FindColour(m_colourWheel, "Yellow"));
    blueButton.whenPressed(new FindColour(m_colourWheel, "Blue"));
    redButton.whenPressed(new FindColour(m_colourWheel, "Red"));
    triggerButton.whenPressed(new spinCounter(m_colourWheel));
    */
    triggerButton.whenPressed(new spinFunction(m_colourWheel));

    dExtend.whenPressed(new Extend(dumptruck));
    dRetract.whenPressed(new Retract(dumptruck));
    dSpinForward.whileHeld(new DumpSpin(dumptruck, 0.6));
    dSpinReverse.whileHeld(new DumpSpin(dumptruck, -0.6));


  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
      switch(m_chooser.getSelected()) {
        case autonomous2:
          m_autoCommand = new spinCounter(m_colourWheel);
          break;
        case autonomous1:
          m_autoCommand = new DisplayColor(m_colourWheel);
          break;
        default:
          break;
      };
    return m_autoCommand;
  }
}
