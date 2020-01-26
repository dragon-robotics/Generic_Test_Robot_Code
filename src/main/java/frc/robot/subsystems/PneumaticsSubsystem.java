/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class PneumaticsSubsystem extends SubsystemBase {
  
  private NetworkTableEntry compressor_nt;

  private final Compressor compressor = new Compressor();

  private NetworkTableEntry doubleSolenoid_1_nt;
  private NetworkTableEntry doubleSolenoid_2_nt;
  private NetworkTableEntry doubleSolenoid_3_nt;
  private NetworkTableEntry doubleSolenoid_4_nt;

  private final DoubleSolenoid doubleSolenoid_1 = new DoubleSolenoid(1, 2);
  private final DoubleSolenoid doubleSolenoid_2 = new DoubleSolenoid(3, 4);
  private final DoubleSolenoid doubleSolenoid_3 = new DoubleSolenoid(5, 6);
  private final DoubleSolenoid doubleSolenoid_4 = new DoubleSolenoid(7, 8);

  /**
   * Creates a new PneumaticsSubsystem.
   */
  public PneumaticsSubsystem() {
    // Initialize Shuffleboard //
    compressor_nt = Shuffleboard
                    .getTab("Test Pneumatics")
                    .add("Compressor", false)
                    .withWidget(BuiltInWidgets.kToggleSwitch)
                    .getEntry();
    
    doubleSolenoid_1_nt = Shuffleboard
                    .getTab("Test Pneumatics")
                    .add("Double Solenoid 1", false)
                    .withWidget(BuiltInWidgets.kSpeedController)
                    .getEntry();

    doubleSolenoid_2_nt = Shuffleboard
                    .getTab("Test Pneumatics")
                    .add("Double Solenoid 2", false)
                    .withWidget(BuiltInWidgets.kToggleSwitch)
                    .getEntry();

    doubleSolenoid_3_nt = Shuffleboard
                    .getTab("Test Pneumatics")
                    .add("Double Solenoid 3", false)
                    .withWidget(BuiltInWidgets.kToggleSwitch)
                    .getEntry();

    doubleSolenoid_4_nt = Shuffleboard
                    .getTab("Test Pneumatics")
                    .add("Double Solenoid 4", false)
                    .withWidget(BuiltInWidgets.kToggleSwitch)
                    .getEntry();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void TestTalons(){
    
  }
}
