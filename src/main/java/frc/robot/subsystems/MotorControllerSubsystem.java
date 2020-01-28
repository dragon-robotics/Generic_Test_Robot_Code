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
  
  private final List<TalonSRX> talonSrxList = new ArrayList<TalonSRX>(
    Arrays.asList(
      new TalonSRX(Constants.TALONSRX_1),
      new TalonSRX(Constants.TALONSRX_2),
      new TalonSRX(Constants.TALONSRX_3),
      new TalonSRX(Constants.TALONSRX_4)
    )
  );

  /* Talon FX */
  private List<NetworkTableEntry> talonFxList_nte;

  private final List<TalonFX> talonFxList = new ArrayList<TalonFX>(
    Arrays.asList(
      new TalonFX(Constants.TALONFX_1),
      new TalonFX(Constants.TALONFX_2),
      new TalonFX(Constants.TALONFX_3),
      new TalonFX(Constants.TALONFX_4)
    )
  );

  /* Spark MAX */
  private List<NetworkTableEntry> sparkMaxList_nte;

  private final List<CANSparkMax> sparkMaxList = new ArrayList<CANSparkMax>(
    Arrays.asList(
      new CANSparkMax(Constants.SPARKMAX_1, MotorType.kBrushless),
      new CANSparkMax(Constants.SPARKMAX_2, MotorType.kBrushless)
    )
  );

  /**
   * Creates a new MotorControllerSubsystem.
   */
  public MotorControllerSubsystem() {
    talonSrxList_nte = new ArrayList<NetworkTableEntry>();
    talonFxList_nte = new ArrayList<NetworkTableEntry>();
    sparkMaxList_nte = new ArrayList<NetworkTableEntry>();
    
    /* Initialize Shuffleboard */
    
    // Add TalonSRX //
    for(int i = 1; i <= talonSrxList.size(); i++){
      talonSrxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonSRX " + i, 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .getEntry()
      );
    }

    // Add TalonFX //
    for (int i = 1; i <= talonFxList.size(); i++) {
      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i, 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .getEntry()
      );

      // Add TalonFX sensors //
      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i + ": Absolute Position", 0)
        .getEntry()
      );

      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i + ": Position", 0)
        .getEntry()
      );

      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i + ": Velocity", 0)
        .getEntry()
      );
    }

    // Add SparkMax //
    for (int i = 1; i <= sparkMaxList.size(); i++) {
      sparkMaxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("SparkMax " + i, 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .getEntry()
      );
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    TestMotorController();        // Set Motor Controller(s)
    UpdateTalonFXSensorValues();  // Update Talon FX sensor values
  }

  public void UpdateTalonFXSensorValues(){
    for (int i = 0; i < talonFxList.size(); i+=4) {
      talonFxList.get(i).set(
        ControlMode.PercentOutput, 
        talonFxList_nte.get(i).getDouble(0)
      );
    }
  }

  public void TestMotorController(){
    // Set TalonSRXs based on slider value from the Shuffleboard //
    for (int i = 0; i < talonSrxList.size(); i++) {
      talonSrxList.get(i).set(
        ControlMode.PercentOutput,
        talonSrxList_nte.get(i).getDouble(0)
      );
    }
    
    // Set TalonFXs based on slider value from the Shuffleboard //
    for (int i = 0; i < talonFxList.size(); i++) {
      talonFxList.get(i).set(
        ControlMode.PercentOutput, 
        talonFxList_nte.get(i).getDouble(0)
      );
    }
    
    // Set SparkMaxes based on slider value from the Shuffleboard //
    for (int i = 0; i < sparkMaxList.size(); i++) {
      sparkMaxList.get(i).set(
        sparkMaxList_nte.get(i).getDouble(0)
      );
    }
  }
}
