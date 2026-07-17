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


    public static LoggedNetworkNumber logHoodMMKS = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kS", 0);
    public static LoggedNetworkNumber logHoodMMKV = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kV", 0);
    public static LoggedNetworkNumber logHoodMMKA = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kA", 0);
    public static LoggedNetworkNumber logHoodMMKP = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kP", 0);
    public static LoggedNetworkNumber logHoodMMKI = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kI", 0);
    public static LoggedNetworkNumber logHoodMMKD = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kD", 0);
    public static LoggedNetworkNumber logHoodMMKG = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kG", 0);

    public static LoggedNetworkNumber logHoodMMVeloc = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/Velocity", 0);
    public static LoggedNetworkNumber logHoodMMAccel = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/Acceleration", 0);

    public static LoggedNetworkNumber logShooterVelocMMKS = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/kS", 0);
    public static LoggedNetworkNumber logShooterVelocMMKV = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/kV", 0);
    public static LoggedNetworkNumber logShooterVelocMMKA = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/kA", 0);
    public static LoggedNetworkNumber logShooterVelocMMKP = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/kP", 0);

    public static LoggedNetworkNumber logShooterVelocMMAccel = new LoggedNetworkNumber("Rebuilt/Hood/Tuning/VelocMM/Acceleration", 0);

    public static TalonFXConfiguration shooterMotorConfig = new TalonFXConfiguration();
    public static TalonFXConfiguration hoodMotorConfig = new TalonFXConfiguration();

    private ShooterConstants () {
        configureShooterMotors();
        configureHoodMotors();
    }

    public void configureHoodMotors() {

        hoodMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        hoodMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        
        hoodMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        hoodMotorConfig.CurrentLimits.StatorCurrentLimit = 40;
        hoodMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        hoodMotorConfig.CurrentLimits.SupplyCurrentLimit = 20;

        Slot0Configs slot0Config = new Slot0Configs().withKS(logHoodMMKS.get())
                                                    .withKV(logHoodMMKV.get())
                                                    .withKA(logHoodMMKA.get())
                                                    .withKP(logHoodMMKP.get())
                                                    .withKI(logHoodMMKI.get())
                                                    .withKD(logHoodMMKD.get())
                                                    .withKG(logHoodMMKG.get());
        shooterMotorConfig.Slot0 = slot0Config;

        MotionMagicConfigs mmConfigs = new MotionMagicConfigs().withMotionMagicAcceleration(logHoodMMAccel.get())
                                                               .withMotionMagicCruiseVelocity(logHoodMMVeloc.get());
        shooterMotorConfig.MotionMagic = mmConfigs;
        
        mHoodFx.getConfigurator().apply(hoodMotorConfig);
    }


   private void configureShooterMotors() {
        shooterMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        shooterMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        shooterMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        shooterMotorConfig.CurrentLimits.StatorCurrentLimit = 80;
        shooterMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        shooterMotorConfig.CurrentLimits.SupplyCurrentLowerTime = 3;
        shooterMotorConfig.CurrentLimits.SupplyCurrentLimit = 40;
        shooterMotorConfig.CurrentLimits.SupplyCurrentLowerLimit = 25;


        Slot0Configs slot0Config = new Slot0Configs().withKS(logShooterVelocMMKS.get())
                                                    .withKV(logShooterVelocMMKV.get())
                                                    .withKA(logShooterVelocMMKA.get())
                                                    .withKP(logShooterVelocMMKP.get());

        shooterMotorConfig.Slot0 = slot0Config;

        shooterMotorConfig.MotionMagic.MotionMagicAcceleration = logShooterVelocMMAccel.get();

        mShooterFx.getConfigurator().apply(shooterMotorConfig);
        mShooterFollowerFx.setControl(new Follower(mShooterFx.getDeviceID(), MotorAlignmentValue.Opposed));
    }


}
