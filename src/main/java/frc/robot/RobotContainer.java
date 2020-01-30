/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Limelight_Subsystem;
import frc.robot.subsystems.MotorControllerSubsystem;
import frc.robot.subsystems.NavXIMU_Subsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final MotorControllerSubsystem m_motorControllerSubsystem = new MotorControllerSubsystem();
  // private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem();
  // private final Limelight_Subsystem m_limelightSubsystem = new Limelight_Subsystem();
  // private final NavXIMU_Subsystem m_navXIMUSubsystem = new NavXIMU_Subsystem();

    /* Robot Driver - AKA Driver 1 */
    public static final Joystick j_stick_driver = new Joystick(Constants.J_STICK_DRIVER);  // Drive joystick (0) initialization
    public static final JoystickButton j_stick_driver_LB = new JoystickButton(j_stick_driver, Constants.BUTTON_RIGHT);              // Left button
    public static final JoystickButton j_stick_driver_RB = new JoystickButton(j_stick_driver, Constants.BUTTON_LEFT);               // Right button 
    public static final JoystickButton j_stick_driver_X = new JoystickButton(j_stick_driver, Constants.BUTTON_X);                   // X button
    public static final JoystickButton j_stick_driver_Y = new JoystickButton(j_stick_driver, Constants.BUTTON_Y);                   // Y button
    public static final JoystickButton j_stick_driver_A = new JoystickButton(j_stick_driver, Constants.BUTTON_A);                   // A button
    public static final JoystickButton j_stick_driver_B = new JoystickButton(j_stick_driver, Constants.BUTTON_B);                   // B button
    public static final JoystickButton j_stick_driver_Back = new JoystickButton(j_stick_driver, Constants.BUTTON_BACK);             // Back button
    public static final JoystickButton j_stick_driver_Start = new JoystickButton(j_stick_driver, Constants.BUTTON_START);           // Start button
    public static final JoystickButton j_stick_driver_leftStick = new JoystickButton(j_stick_driver, Constants.BUTTON_LEFTSTICK);   // Left-Stick button
    public static final JoystickButton j_stick_driver_rightStick = new JoystickButton(j_stick_driver, Constants.BUTTON_RIGHTSTICK); // Right-Stick button

    /* Robot Controller - AKA Driver 2 */
    public static final Joystick j_stick_control = new Joystick(Constants.J_STICK_CONTROL);  // Control joystick (1) initialization
    public static final JoystickButton j_stick_control_LB = new JoystickButton(j_stick_control, Constants.BUTTON_RIGHT);              // Left button
    public static final JoystickButton j_stick_control_RB = new JoystickButton(j_stick_control, Constants.BUTTON_LEFT);               // Right button 
    public static final JoystickButton j_stick_control_X = new JoystickButton(j_stick_control, Constants.BUTTON_X);                   // X button
    public static final JoystickButton j_stick_control_Y = new JoystickButton(j_stick_control, Constants.BUTTON_Y);                   // Y button
    public static final JoystickButton j_stick_control_A = new JoystickButton(j_stick_control, Constants.BUTTON_A);                   // A button
    public static final JoystickButton j_stick_control_B = new JoystickButton(j_stick_control, Constants.BUTTON_B);                   // B button
    public static final JoystickButton j_stick_control_Back = new JoystickButton(j_stick_control, Constants.BUTTON_BACK);             // Back button
    public static final JoystickButton j_stick_control_Start = new JoystickButton(j_stick_control, Constants.BUTTON_START);           // Start button
    public static final JoystickButton j_stick_control_leftStick = new JoystickButton(j_stick_control, Constants.BUTTON_LEFTSTICK);   // Left-Stick button
    public static final JoystickButton j_stick_control_rightStick = new JoystickButton(j_stick_control, Constants.BUTTON_RIGHTSTICK); // Right-Stick button

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
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
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
