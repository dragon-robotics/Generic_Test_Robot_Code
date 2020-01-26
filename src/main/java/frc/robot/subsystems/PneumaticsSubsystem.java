/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class PneumaticsSubsystem extends SubsystemBase {
  
  private String tabTitle;
  
  private NetworkTableEntry compressor_nt;

  private final Compressor compressor = new Compressor();

  private List<SendableChooser<Value>> doubleSolenoidPositionChooserList;

  private final List<DoubleSolenoid> doubleSolenoidList = new ArrayList<DoubleSolenoid>(Arrays.asList(
    new DoubleSolenoid(1, 2),
    new DoubleSolenoid(3, 4),
    new DoubleSolenoid(5, 6),
    new DoubleSolenoid(7, 8)
  ));

  /**
   * Creates a new PneumaticsSubsystem.
   */
  public PneumaticsSubsystem() {
    tabTitle = "Test Pneumatics";
    
    // Initialize Shuffleboard //
    compressor_nt = Shuffleboard
                    .getTab(tabTitle)
                    .add("Compressor", false)
                    .withWidget(BuiltInWidgets.kToggleSwitch)
                    .getEntry();
    
    // Initialize list of sendable chooser for the pistons //
    for(int i = 1; i <= 4; i++){
      SendableChooser<Value> doubleSolenoidPositionChooser = new SendableChooser<Value>();
      doubleSolenoidPositionChooser.setDefaultOption("kOff", Value.kOff);
      doubleSolenoidPositionChooser.addOption("kForward", Value.kForward);
      doubleSolenoidPositionChooser.addOption("kReverse", Value.kReverse);
      
      Shuffleboard
      .getTab(tabTitle)
      .add("Double Solenoid " + i, doubleSolenoidPositionChooser)
      .withWidget(BuiltInWidgets.kComboBoxChooser);
      
      doubleSolenoidPositionChooserList.add(doubleSolenoidPositionChooser);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    TestPneumatics();
  }

  public void TestPneumatics(){
    compressor.setClosedLoopControl(compressor_nt.getBoolean(false));
    
    for (int i = 0; i < 4; i++) {
      Value setting = doubleSolenoidPositionChooserList.get(i).getSelected();
      doubleSolenoidList.get(i).set(setting);
    }
  }
}
