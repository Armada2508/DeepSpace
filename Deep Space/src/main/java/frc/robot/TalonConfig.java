package frc.robot;

public class TalonConfig {
	public TalonConfig(double p,double i,double d,double f,double ramp, double rpm, int peakVoltage, int peakCurrent){
		P = p;
		I = i;
		D = d;
		F = f;
		Ramp = ramp;
		MaxRPM = rpm;
		PeakVoltage = peakVoltage;
		PeakCurrent = peakCurrent;
	}
	
	public final double Ramp;
	public final double MaxRPM;
	public final double P;
	public final double I;
	public final double D;
	public final double F;
	public final double PeakVoltage;
	public final int PeakCurrent;
}