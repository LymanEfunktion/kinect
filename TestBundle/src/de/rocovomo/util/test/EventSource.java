package de.rocovomo.util.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

public class EventSource extends Observable implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			final InputStreamReader isr = new InputStreamReader(System.in);
			final BufferedReader br = new BufferedReader(isr);
			while (true) {
				String response = br.readLine();
				if (response.startsWith("f") || response.startsWith("b")) {
					setChanged();
					notifyObservers(response);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
