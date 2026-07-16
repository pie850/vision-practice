package frc.robot.subsystems.ShooterSubsystem;

import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterConstants extends SubsystemBase {
 
    


    public static TalonFX mShooterFx = new TalonFX(0, Constants.MAIN_SYSTEMS_CANBUS);


    public static LoggedNetworkNumber logShooterMMKS = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kS", 0);
    public static LoggedNetworkNumber logShooterMMKV = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kV", 0);
    public static LoggedNetworkNumber logShooterMMKA = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kA", 0);
    public static LoggedNetworkNumber logShooterMMKP = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kP", 0);
    public static LoggedNetworkNumber logShooterMMKI = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kI", 0);
    public static LoggedNetworkNumber logShooterMMKD = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kD", 0);
    public static LoggedNetworkNumber logShooterMMKG = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/kG", 0);

    
    public static LoggedNetworkNumber logShooterMMVeloc = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/Velocity", 0);
    public static LoggedNetworkNumber logShooterMMAccel = new LoggedNetworkNumber("Rebuilt/Shooter/Tuning/MM/Acceleration", 0);

    public static TalonFXConfiguration motorConfig = new TalonFXConfiguration();

    private ShooterConstants () {
        
    }

    public void configureAngleMotors() {
        

        angleMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        angleMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        
        angleMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        angleMotorConfig.CurrentLimits.StatorCurrentLimit = 40;
        angleMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        angleMotorConfig.CurrentLimits.SupplyCurrentLimit = 20;

        Slot0Configs slot0Config = new Slot0Configs().withKS(logHoodMMKS.get())
                                                    .withKV(logHoodMMKV.get())
                                                    .withKA(logHoodMMKA.get())
                                                    .withKP(logHoodMMKP.get())
                                                    .withKI(logHoodMMKI.get())
                                                    .withKD(logHoodMMKD.get())
                                                    .withKG(logHoodMMKG.get());
        angleMotorConfig.Slot0 = slot0Config;
        
        Slot1Configs slot1Config = new Slot1Configs().withKS(logHoodMMKS.get())
                                                    .withKV(logHoodMMKV.get())
                                                    .withKA(logHoodMMKA.get())
                                                    .withKP(logHoodMMKP.get())
                                                    .withKI(logHoodMMKI.get())
                                                    .withKD(logHoodMMKD.get())
                                                    .withKG(logHoodMMKG.get());
        angleMotorConfig.Slot1 = slot1Config;

        MotionMagicConfigs mmConfigs = new MotionMagicConfigs().withMotionMagicAcceleration(logHoodMMAccel.get())
                                                               .withMotionMagicCruiseVelocity(logHoodMMVeloc.get());
        angleMotorConfig.MotionMagic = mmConfigs;

        angleMotorConfig.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        angleMotorConfig.SoftwareLimitSwitch.ReverseSoftLimitEnable = false;
        angleMotorConfig.SoftwareLimitSwitch.ForwardSoftLimitThreshold = 12.856934; // Hood angle limit
        angleMotorConfig.SoftwareLimitSwitch.ReverseSoftLimitThreshold = 0;

        angleMotor.getConfigurator().apply(angleMotorConfig);
    }


}
