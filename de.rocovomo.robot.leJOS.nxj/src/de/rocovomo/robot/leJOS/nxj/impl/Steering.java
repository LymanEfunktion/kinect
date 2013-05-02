package de.rocovomo.robot.leJOS.nxj.impl;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

public class Steering{

	private RemoteMotor steering = Motor.A;
	private RemoteMotor propulsion = Motor.C;
	
	private int steeringAngle = 45;
	private int propulsionAngleRight = 510;
	private int propulsionAngleLeft = 510;
	
	public void turnLeft() {
		steering.rotate(-steeringAngle);
		propulsion.rotate(propulsionAngleLeft);
		steering.rotate(steeringAngle);
	}

	public void turnRight() {
		steering.rotate(steeringAngle);
		propulsion.rotate(propulsionAngleRight);
		steering.rotate(-steeringAngle);
	}


}
