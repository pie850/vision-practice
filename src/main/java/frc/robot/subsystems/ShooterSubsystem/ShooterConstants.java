package frc.robot.subsystems.ShooterSubsystem;

import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterConstants extends SubsystemBase {
 
    


    public static TalonFX mShooterFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);
    public static TalonFX mShooterFollowerFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);
    public static TalonFX mHoodFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);


    public static LoggedNetworkNumber logShooterMMKS = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kS", 0);
    public static LoggedNetworkNumber logShooterMMKV = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kV", 0);
    public static LoggedNetworkNumber logShooterMMKA = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kA", 0);
    public static LoggedNetworkNumber logShooterMMKP = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kP", 0);
    public static LoggedNetworkNumber logShooterMMKI = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kI", 0);
    public static LoggedNetworkNumber logShooterMMKD = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kD", 0);
    public static LoggedNetworkNumber logShooterMMKG = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kG", 0);

    public static LoggedNetworkNumber logShooterMMVeloc = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/Velocity", 0);
    public static LoggedNetworkNumber logShooterMMAccel = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/Acceleration", 0);

    public static LoggedNetworkNumber logHoodVelocMMKS = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/kS", 0);
    public static LoggedNetworkNumber logHoodVelocMMKV = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/kV", 0);
    public static LoggedNetworkNumber logHoodVelocMMKA = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/kA", 0);
    public static LoggedNetworkNumber logHoodVelocMMKP = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/kP", 0);

    public static LoggedNetworkNumber logHoodVelocMMAccel = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/Acceleration", 0);

    public static TalonFXConfiguration shooterMotorConfig = new TalonFXConfiguration();
    public static TalonFXConfiguration hoodMotorConfig = new TalonFXConfiguration();

    private ShooterConstants () {
        configureHoodMotors();
        configureShooterMotors();
    }

    public void configureShooterMotors() {

        shooterMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        shooterMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        
        shooterMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        shooterMotorConfig.CurrentLimits.StatorCurrentLimit = 40;
        shooterMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        shooterMotorConfig.CurrentLimits.SupplyCurrentLimit = 20;

        Slot0Configs slot0Config = new Slot0Configs().withKS(logShooterMMKS.get())
                                                    .withKV(logShooterMMKV.get())
                                                    .withKA(logShooterMMKA.get())
                                                    .withKP(logShooterMMKP.get())
                                                    .withKI(logShooterMMKI.get())
                                                    .withKD(logShooterMMKD.get())
                                                    .withKG(logShooterMMKG.get());
        shooterMotorConfig.Slot0 = slot0Config;

        MotionMagicConfigs mmConfigs = new MotionMagicConfigs().withMotionMagicAcceleration(logShooterMMAccel.get())
                                                               .withMotionMagicCruiseVelocity(logShooterMMVeloc.get());
        shooterMotorConfig.MotionMagic = mmConfigs;

    }


   private void configureHoodMotors() {
        hoodMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        hoodMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        hoodMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        hoodMotorConfig.CurrentLimits.StatorCurrentLimit = 80;
        hoodMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        hoodMotorConfig.CurrentLimits.SupplyCurrentLowerTime = 3;
        hoodMotorConfig.CurrentLimits.SupplyCurrentLimit = 40;
        hoodMotorConfig.CurrentLimits.SupplyCurrentLowerLimit = 25;


        Slot0Configs slot0Config = new Slot0Configs().withKS(logHoodVelocMMKS.get())
                                                    .withKV(logHoodVelocMMKV.get())
                                                    .withKA(logHoodVelocMMKA.get())
                                                    .withKP(logHoodVelocMMKP.get());

        hoodMotorConfig.Slot0 = slot0Config;

        hoodMotorConfig.MotionMagic.MotionMagicAcceleration = logHoodVelocMMAccel.get();

        mShooterFx.getConfigurator().apply(hoodMotorConfig);
        mShooterFollowerFx.setControl(new Follower(mShooterFx.getDeviceID(), MotorAlignmentValue.Opposed));
    }


}
