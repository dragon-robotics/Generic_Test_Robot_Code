/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class NavXIMU_Subsystem extends SubsystemBase {
  private AHRS ahrs;
  private String tabTitle;
  private ShuffleboardTab navXTab;

  /* NetworkTableEntry for each IMU data value */
  private NetworkTableEntry imuConnected_nte;
  private NetworkTableEntry imuIsCalibrating_nte;
  private NetworkTableEntry imuYaw_nte;
  private NetworkTableEntry imuPitch_nte;
  private NetworkTableEntry imuRoll_nte;

  private NetworkTableEntry imuCompassHeader_nte;
  private NetworkTableEntry imuFusedHeading_nte;

  private NetworkTableEntry imuTotalYaw_nte;
  private NetworkTableEntry imuYawRateDPS_nte;

  private NetworkTableEntry imuAccelX_nte;
  private NetworkTableEntry imuAccelY_nte;
  private NetworkTableEntry imuIsMoving_nte;
  private NetworkTableEntry imuIsRotating_nte;

  private NetworkTableEntry velocityX_nte;
  private NetworkTableEntry velocityY_nte;
  private NetworkTableEntry displacementX_nte;
  private NetworkTableEntry displacementY_nte;

  private NetworkTableEntry rawGyroX_nte;
  private NetworkTableEntry rawGyroY_nte;
  private NetworkTableEntry rawGyroZ_nte;
  private NetworkTableEntry rawAccelX_nte;
  private NetworkTableEntry rawAccelY_nte;
  private NetworkTableEntry rawAccelZ_nte;
  private NetworkTableEntry rawMagX_nte;
  private NetworkTableEntry rawMagY_nte;
  private NetworkTableEntry rawMagZ_nte;
  private NetworkTableEntry imuTempC_nte;
  private NetworkTableEntry imuTimestamp_nte;

  private NetworkTableEntry yawAxisDirection_nte;
  private NetworkTableEntry yawAxis_nte;

  private NetworkTableEntry firmwareVersion_nte;

  private NetworkTableEntry quarternionW_nte;
  private NetworkTableEntry quarternionX_nte;
  private NetworkTableEntry quarternionY_nte;
  private NetworkTableEntry quarternionZ_nte;

  private NetworkTableEntry imuByteCount_nte;
  private NetworkTableEntry imuUpdateCount_nte;

  /**
   * Creates a new NavXIMU_Subsystem.
   */
  public NavXIMU_Subsystem() {
    try {
      ahrs = new AHRS(SPI.Port.kMXP);
      ahrs.enableLogging(true);
      ahrs.zeroYaw();

      tabTitle = "NavXTab";
      navXTab = Shuffleboard.getTab(tabTitle);

      DisplayNavXData();
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    UpdateNavXData();
  }

  private void DisplayNavXData() {
    /* Display 6-axis Processed Angle Data */
    imuConnected_nte = navXTab.add("IMU_Connected", ahrs.isConnected()).getEntry();
    imuIsCalibrating_nte = navXTab.add("IMU_IsCalibrating", ahrs.isCalibrating()).getEntry();
    imuYaw_nte = navXTab.add("IMU_Yaw", ahrs.getYaw()).getEntry();
    imuPitch_nte = navXTab.add("IMU_Pitch", ahrs.getPitch()).getEntry();
    imuRoll_nte = navXTab.add("IMU_Roll", ahrs.getRoll()).getEntry();

    /* Display tilt-corrected, Magnetometer-based heading (requires */
    /* magnetometer calibration to be useful) */

    imuCompassHeader_nte = navXTab.add("IMU_CompassHeading", ahrs.getCompassHeading()).getEntry();

    /* Display 9-axis Heading (requires magnetometer calibration to be useful) */
    imuFusedHeading_nte = navXTab.add("IMU_FusedHeading", ahrs.getFusedHeading()).getEntry();

    /* These functions are compatible w/the WPI Gyro Class, providing a simple */
    /* path for upgrading from the Kit-of-Parts gyro to the navx MXP */

    imuTotalYaw_nte = navXTab.add("IMU_TotalYaw", ahrs.getAngle()).getEntry();
    imuYawRateDPS_nte = navXTab.add("IMU_YawRateDPS", ahrs.getRate()).getEntry();

    /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */

    imuAccelX_nte = navXTab.add("IMU_Accel_X", ahrs.getWorldLinearAccelX()).getEntry();
    imuAccelY_nte = navXTab.add("IMU_Accel_Y", ahrs.getWorldLinearAccelY()).getEntry();
    imuIsMoving_nte = navXTab.add("IMU_IsMoving", ahrs.isMoving()).getEntry();
    imuIsRotating_nte = navXTab.add("IMU_IsRotating", ahrs.isRotating()).getEntry();

    /* Display estimates of velocity/displacement. Note that these values are */
    /* not expected to be accurate enough for estimating robot position on a */
    /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
    /* of these errors due to single (velocity) integration and especially */
    /* double (displacement) integration. */

    velocityX_nte = navXTab.add("Velocity_X", ahrs.getVelocityX()).getEntry();
    velocityY_nte = navXTab.add("Velocity_Y", ahrs.getVelocityY()).getEntry();
    displacementX_nte = navXTab.add("Displacement_X", ahrs.getDisplacementX()).getEntry();
    displacementY_nte = navXTab.add("Displacement_Y", ahrs.getDisplacementY()).getEntry();

    /* Display Raw Gyro/Accelerometer/Magnetometer Values */
    /* NOTE: These values are not normally necessary, but are made available */
    /* for advanced users. Before using this data, please consider whether */
    /* the processed data (see above) will suit your needs. */

    rawGyroX_nte = navXTab.add("RawGyro_X", ahrs.getRawGyroX()).getEntry();
    rawGyroY_nte = navXTab.add("RawGyro_Y", ahrs.getRawGyroY()).getEntry();
    rawGyroZ_nte = navXTab.add("RawGyro_Z", ahrs.getRawGyroZ()).getEntry();
    rawAccelX_nte = navXTab.add("RawAccel_X", ahrs.getRawAccelX()).getEntry();
    rawAccelY_nte = navXTab.add("RawAccel_Y", ahrs.getRawAccelY()).getEntry();
    rawAccelZ_nte = navXTab.add("RawAccel_Z", ahrs.getRawAccelZ()).getEntry();
    rawMagX_nte = navXTab.add("RawMag_X", ahrs.getRawMagX()).getEntry();
    rawMagY_nte = navXTab.add("RawMag_Y", ahrs.getRawMagY()).getEntry();
    rawMagZ_nte = navXTab.add("RawMag_Z", ahrs.getRawMagZ()).getEntry();
    imuTempC_nte = navXTab.add("IMU_Temp_C", ahrs.getTempC()).getEntry();
    imuTimestamp_nte = navXTab.add("IMU_Timestamp", ahrs.getLastSensorTimestamp()).getEntry();

    /* Omnimount Yaw Axis Information */
    /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount */
    AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
    yawAxisDirection_nte = navXTab.add("YawAxisDirection", yaw_axis.up ? "Up" : "Down").getEntry();
    yawAxis_nte = navXTab.add("YawAxis", yaw_axis.board_axis.getValue()).getEntry();

    /* Sensor Board Information */
    firmwareVersion_nte = navXTab.add("FirmwareVersion", ahrs.getFirmwareVersion()).getEntry();

    /* Quaternion Data */
    /* Quaternions are fascinating, and are the most compact representation of */
    /* orientation data. All of the Yaw, Pitch and Roll Values can be derived */
    /* from the Quaternions. If interested in motion processing, knowledge of */
    /* Quaternions is highly recommended. */
    quarternionW_nte = navXTab.add("QuaternionW", ahrs.getQuaternionW()).getEntry();
    quarternionX_nte = navXTab.add("QuaternionX", ahrs.getQuaternionX()).getEntry();
    quarternionY_nte = navXTab.add("QuaternionY", ahrs.getQuaternionY()).getEntry();
    quarternionZ_nte = navXTab.add("QuaternionZ", ahrs.getQuaternionZ()).getEntry();

    /* Connectivity Debugging Support */
    imuByteCount_nte = navXTab.add("IMU_Byte_Count", ahrs.getByteCount()).getEntry();
    imuUpdateCount_nte = navXTab.add("IMU_Update_Count", ahrs.getUpdateCount()).getEntry();
  }

  private void UpdateNavXData() {
    /* Display 6-axis Processed Angle Data */
    imuConnected_nte.setBoolean(ahrs.isConnected());
    imuIsCalibrating_nte.setBoolean(ahrs.isCalibrating());
    imuYaw_nte.setDouble(ahrs.getYaw());
    imuPitch_nte.setDouble(ahrs.getPitch());
    imuRoll_nte.setDouble(ahrs.getRoll());

    /* Display tilt-corrected, Magnetometer-based heading (requires */
    /* magnetometer calibration to be useful) */

    imuCompassHeader_nte.setDouble(ahrs.getCompassHeading());

    /* Display 9-axis Heading (requires magnetometer calibration to be useful) */
    imuFusedHeading_nte.setDouble(ahrs.getFusedHeading());

    /* These functions are compatible w/the WPI Gyro Class, providing a simple */
    /* path for upgrading from the Kit-of-Parts gyro to the navx MXP */

    imuTotalYaw_nte.setDouble(ahrs.getAngle());
    imuYawRateDPS_nte.setDouble(ahrs.getRate());

    /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */

    imuAccelX_nte.setDouble(ahrs.getWorldLinearAccelX());
    imuAccelY_nte.setDouble(ahrs.getWorldLinearAccelY());
    imuIsMoving_nte.setBoolean(ahrs.isMoving());
    imuIsRotating_nte.setBoolean(ahrs.isRotating());

    /* Display estimates of velocity/displacement. Note that these values are */
    /* not expected to be accurate enough for estimating robot position on a */
    /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
    /* of these errors due to single (velocity) integration and especially */
    /* double (displacement) integration. */

    velocityX_nte.setDouble(ahrs.getVelocityX());
    velocityY_nte.setDouble(ahrs.getVelocityY());
    displacementX_nte.setDouble(ahrs.getDisplacementX());
    displacementY_nte.setDouble(ahrs.getDisplacementY());

    /* Display Raw Gyro/Accelerometer/Magnetometer Values */
    /* NOTE: These values are not normally necessary, but are made available */
    /* for advanced users. Before using this data, please consider whether */
    /* the processed data (see above) will suit your needs. */

    rawGyroX_nte.setDouble(ahrs.getRawGyroX());
    rawGyroY_nte.setDouble(ahrs.getRawGyroY());
    rawGyroZ_nte.setDouble(ahrs.getRawGyroZ());
    rawAccelX_nte.setDouble(ahrs.getRawAccelX());
    rawAccelY_nte.setDouble(ahrs.getRawAccelY());
    rawAccelZ_nte.setDouble(ahrs.getRawAccelZ());
    rawMagX_nte.setDouble(ahrs.getRawMagX());
    rawMagY_nte.setDouble(ahrs.getRawMagY());
    rawMagZ_nte.setDouble(ahrs.getRawMagZ());
    imuTempC_nte.setDouble(ahrs.getTempC());
    imuTimestamp_nte.setDouble(ahrs.getLastSensorTimestamp());

    /* Omnimount Yaw Axis Information */
    /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount */
    AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
    yawAxisDirection_nte.setString(yaw_axis.up ? "Up" : "Down");
    yawAxis_nte.setDouble(yaw_axis.board_axis.getValue());

    /* Sensor Board Information */
    firmwareVersion_nte.setString(ahrs.getFirmwareVersion());

    /* Quaternion Data */
    /* Quaternions are fascinating, and are the most compact representation of */
    /* orientation data. All of the Yaw, Pitch and Roll Values can be derived */
    /* from the Quaternions. If interested in motion processing, knowledge of */
    /* Quaternions is highly recommended. */
    quarternionW_nte.setDouble(ahrs.getQuaternionW());
    quarternionX_nte.setDouble(ahrs.getQuaternionX());
    quarternionY_nte.setDouble(ahrs.getQuaternionY());
    quarternionZ_nte.setDouble(ahrs.getQuaternionZ());

    /* Connectivity Debugging Support */
    imuByteCount_nte.setDouble(ahrs.getByteCount());
    imuUpdateCount_nte.setDouble(ahrs.getUpdateCount());
  }
}
