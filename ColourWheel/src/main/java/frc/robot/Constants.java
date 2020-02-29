/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Drivetrain Controls
    public static final int DRIVETRAIN_LEFT_FRONT = 1;
    public static final int DRIVETRAIN_LEFT_REAR = 2;
    public static final int DRIVETRAIN_RIGHT_FRONT = 3;
    public static final int DRIVETRAIN_RIGHT_REAR = 4;

    public static final double TOP_SPEED = 0.7;
    public static final double TURN_SPEED = 1.0;

    //DumpTruck Constants
    public static final int BELT_A = 5;
    public static final int BELT_B = 6;
    public static final double BELT_MAX = 0.5;
    public static final int D_SOLENOID_F = 0;
    public static final int D_SOLENOID_R = 1;

    // Colour Wheel Identifiers
    public static final int CANSPIN = 0;
    public static final int PWMSpin = 0;

    // Colour Wheel ColourCodes
    public static final Color kBLUE = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static final Color kGREEN = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static final Color kRED = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static final Color kYELLOW = ColorMatch.makeColor(0.361, 0.524, 0.113);

    // Pneumatics Constants
    public static final int PN_COMPRESSOR = 0;

    // Joystick Constants - Logitech Extreme
    public static final int OI_DRIVER_CONTROLLER = 0;
    public static final int OI_STICK_Y = 1;
    public static final int OI_STICK_X = 0;
   
    public static final int OI_TRIGGER = 1;
    public static final int OI_BUTTON_2 = 2;
    public static final int OI_BUTTON_R = 7;
    public static final int OI_BUTTON_G = 8;
    public static final int OI_BUTTON_B = 9;
    public static final int OI_BUTTON_Y = 10;
    public static final int OI_BUTTON_7 = 7;
    public static final int OI_BUTTON_3=3;
    public static final int OI_BUTTON_4=4;
    public static final int OI_BUTTON_5=5;
    public static final int OI_BUTTON_6=6;
    public static final int OI_BUTTON_8 = 8;
    public static final int OI_BUTTON_9 = 9;

}
