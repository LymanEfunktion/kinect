package de.rocovomo.robot.leJOS.nxj.impl.test;

import de.rocovomo.robot.api.RobotMovement;
import de.rocovomo.robot.leJOS.nxj.impl.RobotMovementImpl;

public class RobotMovementImplTest {

	public static void main(String[] args) throws InterruptedException {
		RobotMovement robot = new RobotMovementImpl();
		robot.turnRight();
		robot.turnLeft();
//		robot.moveForward();
//		Thread.sleep(500);
//		robot.stopMovement();
	}
}
