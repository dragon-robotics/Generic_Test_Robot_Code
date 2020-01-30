/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MotorControllerSubsystem extends SubsystemBase {
  
  /* Talon SRX */
  private List<NetworkTableEntry> talonSrxList_nte;
  private List<TalonSRX> talonSrxList;

  /* Talon FX */
  private List<NetworkTableEntry> talonFxList_nte;
  private List<TalonFX> talonFxList;

  /* Spark MAX */
  private List<NetworkTableEntry> sparkMaxList_nte;
  private List<CANSparkMax> sparkMaxList;

  /**
   * Creates a new MotorControllerSubsystem.
   */
  public MotorControllerSubsystem() {
    InitializeTalonSrxNte(1);
    // InitializeTalonFxNte(4);
    // InitializeSparkMaxNte(2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    TestMotorController();        // Set Motor Controller(s)
    // UpdateTalonFXSensorValues();  // Update Talon FX sensor values
    // UpdateSparkMaxSensorValues();  // Update SparkMax sensor values
  }

  private void TestMotorController(){
    SetTalonSRX();
    // SetTalonFX();
    // SetSparkMax();
  }

  private void InitializeTalonSrxNte(int motorCount){
    
    talonSrxList = new ArrayList<TalonSRX>();
    
    for(int i = 1; i <= motorCount; i++){
      talonSrxList.add(new TalonSRX(i));
    }
    
    talonSrxList_nte = new ArrayList<NetworkTableEntry>();

    // Add TalonSRX //
    for (int i = 1; i <= talonSrxList.size(); i++) {
      talonSrxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonSRX " + i, 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withSize(3, 1)
        .withPosition(0, (i-1))
        .getEntry()
      );
    }
  }

  private void SetTalonSRX(){
    
    // Set TalonSRXs based on slider value from the Shuffleboard //
    for (int i = 0; i < talonSrxList.size(); i++) {
      talonSrxList.get(i).set(
        ControlMode.PercentOutput,
        talonSrxList_nte.get(i).getDouble(0)
      );
    }
  }
  
  private void InitializeTalonFxNte(int motorCount) {
    
    talonFxList = new ArrayList<TalonFX>();

    for (int i = 1; i <= motorCount; i++) {
      talonFxList.add(new TalonFX(i));
    }
    
    talonFxList_nte = new ArrayList<NetworkTableEntry>();
    
    // Add TalonFX //
    for (int i = 1; i <= talonFxList.size(); i++) {
      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i, 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withSize(3, 1)
        .withPosition(3, (4 * i) - 4)
        .getEntry()
      );

      // Add TalonFX sensors //
      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i + ": Absolute Position", 0)
        .withSize(3, 1)
        .withPosition(3, (4 * i) - 3)
        .getEntry()
      );

      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i + ": Position", 0)
        .withSize(3, 1)
        .withPosition(3, (4 * i) - 2)
        .getEntry()
      );

      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i + ": Velocity", 0)
        .withSize(3, 1)
        .withPosition(3, (4 * i) - 1)
        .getEntry()
      );
    }
  }

  private void SetTalonFX(){
    // Set TalonFXs based on slider value from the Shuffleboard //
    for (int i = 0; i < talonFxList.size(); i++) {
      talonFxList.get(i).set(
        ControlMode.PercentOutput, 
        talonFxList_nte.get(4*i).getDouble(0)
      );
    }
  }

  private void UpdateTalonFXSensorValues() {
    for (int i = 0; i < talonFxList.size(); i += 4) {
      TalonFXSensorCollection sensorCollection = talonFxList.get(i).getSensorCollection();
      double absolute_position = sensorCollection.getIntegratedSensorAbsolutePosition();
      double position = sensorCollection.getIntegratedSensorPosition();
      double velocity = sensorCollection.getIntegratedSensorVelocity();

      talonFxList_nte.get((4 * i) + 1).setDouble(absolute_position); // Update Absolute Position
      talonFxList_nte.get((4 * i) + 2).setDouble(position); // Update Position
      talonFxList_nte.get((4 * i) + 3).setDouble(velocity); // Update Velocity
    }
  }

  private void InitializeSparkMaxNte(int motorCount) {
    sparkMaxList = new ArrayList<CANSparkMax>();

    for (int i = 1; i <= motorCount; i++) {
      sparkMaxList.add(new CANSparkMax(i, MotorType.kBrushless));
    }
    
    sparkMaxList_nte = new ArrayList<NetworkTableEntry>();

    // Add SparkMax //
    for (int i = 1; i <= sparkMaxList.size(); i++) {
      
      // SparkMax Motor Controls //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i, 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 9)
        .getEntry()
      );

      // Average Sampling Depth //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i + ": Average Sampling Depth", 0)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 8)
        .getEntry()
      );

      // Counts per Revolution //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i + ": Counts Per Revolution", 0)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 7)
        .getEntry()
      );

      // Sensor Phase //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i + ": Sensor Phase", 0)
        .withWidget(BuiltInWidgets.kBooleanBox)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 6)
        .getEntry()
      );

      // Measurement Period //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i + ": Measurement Period", 0)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 5)
        .getEntry()
      );

      // Position //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i + ": Position", 0)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 4)
        .getEntry()
      );

      // Position Conversion Factor //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i + ": Position Conversion Factor", 0)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 3)
        .getEntry()
      );
      
      // Velocity //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i + ": Velocity", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 2)
        .getEntry()
      );
      
      // Velocity Conversion Factor //
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i + ": Velocity Conversion Factor", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withSize(3, 1)
        .withPosition(6, (9 * i) - 1)
        .getEntry()
      );
    }
  }

  private void SetSparkMax(){
    // Set SparkMaxes based on slider value from the Shuffleboard //
    for (int i = 0; i < sparkMaxList.size(); i++) {
      sparkMaxList.get(i).set(
        sparkMaxList_nte.get(i).getDouble(0)
      );
    }
  }

  private void UpdateSparkMaxSensorValues() {
    for (int i = 0; i < talonFxList.size(); i += 4) {
      talonFxList.get(i).set(ControlMode.PercentOutput, talonFxList_nte.get(i).getDouble(0));
    }
  }
}
