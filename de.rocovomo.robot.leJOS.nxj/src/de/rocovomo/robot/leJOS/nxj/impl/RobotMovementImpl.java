package de.rocovomo.robot.leJOS.nxj.impl;

import lejos.nxt.Motor;
import de.rocovomo.robot.api.RobotMovement;

public class RobotMovementImpl implements RobotMovement {

	private Propulsion propulsion = new Propulsion();
	private Steering steering = new Steering();

	public RobotMovementImpl() {
		Motor.A.stop();
	}
	
	@Override
	public void moveForward() {
		this.propulsion.moveForward();
	}

	@Override
	public void moveBackward() {
		this.propulsion.moveBackward();
	}

	@Override
	public void turnLeft() {
		this.steering.turnLeft();
	}

	@Override
	public void turnRight() {
		this.steering.turnRight();
	}

	@Override
	public void stopMovement() {
		this.propulsion.stopMovement();
	}

}
