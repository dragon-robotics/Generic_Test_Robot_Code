/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

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
  private NetworkTableEntry talonSrx_1_nt;
  private NetworkTableEntry talonSrx_2_nt;
    
  private final TalonSRX talonSrx_1 = new TalonSRX(Constants.TALONSRX_1);
  private final TalonSRX talonSrx_2 = new TalonSRX(Constants.TALONSRX_2);

  /* Talon FX */
  private NetworkTableEntry talonFx_1_nt;
  private NetworkTableEntry talonFx_2_nt;
  private NetworkTableEntry talonFx_3_nt;
  private NetworkTableEntry talonFx_4_nt;

  private final TalonFX talonFx_1 = new TalonFX(Constants.TALONFX_1);
  private final TalonFX talonFx_2 = new TalonFX(Constants.TALONFX_2);
  private final TalonFX talonFx_3 = new TalonFX(Constants.TALONFX_3);
  private final TalonFX talonFx_4 = new TalonFX(Constants.TALONFX_4);

  /* Spark MAX */
  private NetworkTableEntry sparkMax_1_nt;
  private NetworkTableEntry sparkMax_2_nt;

  private CANSparkMax sparkMax_1 = new CANSparkMax(Constants.SPARKMAX_1, MotorType.kBrushless);
  private CANSparkMax sparkMax_2 = new CANSparkMax(Constants.SPARKMAX_2, MotorType.kBrushless);

  /**
   * Creates a new MotorControllerSubsystem.
   */
  public MotorControllerSubsystem() {
    // Initialize Shuffleboard //
    talonSrx_1_nt = Shuffleboard
                    .getTab("Test Motor Controllers")
                    .add("TalonSRX 1", 0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .getEntry();

    talonSrx_2_nt = Shuffleboard
                    .getTab("Test Motor Controllers")
                    .add("TalonSRX 2", 0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .getEntry();

    talonFx_1_nt = Shuffleboard
                    .getTab("Test Motor Controllers")
                    .add("TalonFX 1", 0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .getEntry();

    talonFx_2_nt = Shuffleboard.getTab("Test Motor Controllers")
                    .add("TalonFX 2", 0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .getEntry();

    talonFx_3_nt = Shuffleboard
                    .getTab("Test Motor Controllers")
                    .add("TalonFX 3", 0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .getEntry();

    talonFx_4_nt = Shuffleboard
                    .getTab("Test Motor Controllers")
                    .add("TalonFX 4", 0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .getEntry();
    
    sparkMax_1_nt = Shuffleboard
                    .getTab("Test Motor Controllers")
                    .add("SparkMax 1", 0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .getEntry();

    sparkMax_2_nt = Shuffleboard
                    .getTab("Test Motor Controllers")
                    .add("SparkMax 2", 0)
                    .withWidget(BuiltInWidgets.kNumberSlider)
                    .getEntry();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    TestMotorController();
  }

  public void TestMotorController(){
    // Set TalonSRXs based on slider value from the Shuffleboard //
    talonSrx_1.set(ControlMode.PercentOutput, talonSrx_1_nt.getDouble(0));
    talonSrx_2.set(ControlMode.PercentOutput, talonSrx_2_nt.getDouble(0));

    // Set TalonFXs based on slider value from the Shuffleboard //
    talonFx_1.set(ControlMode.PercentOutput, talonFx_1_nt.getDouble(0));
    talonFx_2.set(ControlMode.PercentOutput, talonFx_2_nt.getDouble(0));
    talonFx_3.set(ControlMode.PercentOutput, talonFx_3_nt.getDouble(0));
    talonFx_4.set(ControlMode.PercentOutput, talonFx_4_nt.getDouble(0));
    
    // Set SparkMaxes based on slider value from the Shuffleboard //
    sparkMax_1.set(sparkMax_1_nt.getDouble(0));
    sparkMax_2.set(sparkMax_2_nt.getDouble(0));
  }
}
