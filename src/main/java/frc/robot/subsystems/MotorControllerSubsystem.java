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
      new TalonSRX(Constants.TALONSRX_2)
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
    
    // Initialize TalonSRX //
    for(int i = 1; i <= 2; i++){
      talonSrxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonSRX " + i, 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .getEntry()
      );
    }

    // Initialize TalonFX //
    for (int i = 1; i <= 4; i++) {
      talonFxList_nte.add(
        Shuffleboard
        .getTab("Test Motor Controllers")
        .add("TalonFX " + i, 0)
        .withWidget(BuiltInWidgets.kNumberSlider).getEntry()
      );
    }

    // Initialize SparkMax //
    for (int i = 1; i <= 2; i++) {
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
    TestMotorController();
  }

  public void TestMotorController(){
    SetTalonSRX();
    SetTalonFX();
    SetSparkMax();
  }

  public void SetTalonSRX(){
    // Set TalonSRXs based on slider value from the Shuffleboard //
    for (int i = 0; i < 2; i++) {
      talonSrxList.get(i).set(ControlMode.PercentOutput, talonSrxList_nte.get(i).getDouble(0));
    }
  }

  public void SetTalonFX(){
    // Set TalonFXs based on slider value from the Shuffleboard //
    for (int i = 0; i < talonFxList.size(); i++) {
      talonFxList.get(i).set(
        ControlMode.PercentOutput, 
        talonFxList_nte.get(i).getDouble(0)
      );
    }
  }

  public void SetSparkMax(){
    // Set SparkMaxes based on slider value from the Shuffleboard //
    for (int i = 0; i < sparkMaxList.size(); i++) {
      sparkMaxList.get(i).set(sparkMaxList_nte.get(i).getDouble(0));
    }
  }
}
