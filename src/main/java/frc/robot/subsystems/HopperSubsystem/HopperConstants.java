package frc.robot.subsystems.HopperSubsystem;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants;

public class HopperConstants {

    public static final TalonFX mBedFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);
    public static final TalonFX mSideSweeperBottomFx =  new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);

    // not added yet;
    public static final TalonFX mBedFollowerFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);
    public static final TalonFX mTurretTransferFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);
    public static final TalonFX mCornerSweeperFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);
    public static final TalonFX mSideSweeperTopFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);

    private HopperConstants() {
        configureHopperMotor();
    }

    private void configureHopperMotor() {
        TalonFXConfiguration motorConfig = new TalonFXConfiguration();

        motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        // motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        motorConfig.CurrentLimits.StatorCurrentLimit = 80;
        motorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        motorConfig.CurrentLimits.SupplyCurrentLimit = 30;
        motorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        motorConfig.CurrentLimits.SupplyCurrentLowerLimit = 0;
        
        mBedFx.getConfigurator().apply(motorConfig);
        mBedFollowerFx.setControl(new Follower(mBedFx.getDeviceID(), MotorAlignmentValue.Aligned));

        // mTurretTransferFx.getConfigurator().apply(motorConfig);
        // mCornerSweeperFx.getConfigurator().apply(motorConfig);

        mSideSweeperBottomFx.getConfigurator().apply(motorConfig);
        mSideSweeperTopFx.setControl(new Follower(mSideSweeperBottomFx.getDeviceID(), MotorAlignmentValue.Aligned ));

        // mTurretTransferFx.setControl(new Follower(mSideSweeperBottomFx.getDeviceID(), MotorAlignmentValue.Aligned ));
        mCornerSweeperFx.setControl(new Follower(mSideSweeperBottomFx.getDeviceID(), MotorAlignmentValue.Aligned ));


        

    }  
}
