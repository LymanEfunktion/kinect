package de.rocovomo.robot.leJOS.nxj.impl;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

public class Propulsion{

	private RemoteMotor propulsion = Motor.C;

	public void moveForward() {
		this.propulsion.forward();
	}

	public void moveForward(int distance) {
		this.propulsion.rotate(distance);
	}

	public void moveBackward() {
		this.propulsion.backward();
	}

	public void moveBackward(int distance) {
		this.propulsion.rotate(-distance);
	}

	public void stopMovement() {
		this.propulsion.stop();
	}

}
