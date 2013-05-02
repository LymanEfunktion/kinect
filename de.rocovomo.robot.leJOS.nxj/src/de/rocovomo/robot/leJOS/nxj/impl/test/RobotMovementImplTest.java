package de.rocovomo.robot.leJOS.nxj.impl.test;

import de.rocovomo.robot.api.RobotMovement;
import de.rocovomo.robot.leJOS.nxj.impl.RobotMovementImpl;

public class RobotMovementImplTest {

	private static RobotMovement robot;

	public static void main(String[] args) throws InterruptedException {
		robot = new RobotMovementImpl();
		circleRight();
		circleLeft();

		 robot.moveForward();
		 Thread.sleep(500);
		 robot.stopMovement();
	}

	private static void circleLeft() {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}

	private static void circleRight() {
		robot.turnRight();
		robot.turnRight();
		robot.turnRight();
		robot.turnRight();
	}

}
