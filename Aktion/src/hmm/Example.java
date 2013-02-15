package hmm;

import hmm.input.SequenceProvider;

import java.util.List;

import test.SimpleExample.Packet;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationDiscrete;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.OpdfDiscrete;
import be.ac.ulg.montefiore.run.jahmm.OpdfDiscreteFactory;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;

public class Example {

	public static void main(String[] args)
	{
		Hmm<ObservationVector> hmm = buildHmm();
		
		SequenceProvider provider = SequenceProvider.getInstance();
		List<List<ObservationVector>> list = provider.readInput();
	}

	private static Hmm<ObservationVector> buildHmm() {
		// TODO Auto-generated method stub
		Hmm<ObservationVector> hmm = new Hmm<ObservationVector>(4, new OpdfMultiGaussianFactory(2));
		
//		hmm.setPi(0, value);
//		
//		hmm.setAij(i, j, value)
		return null;
	}

//	static Hmm<ObservationDiscrete<Packet>> buildHmm()
//	{	
//		Hmm<ObservationDiscrete<Packet>> hmm = 
//			new Hmm<ObservationDiscrete<Packet>>(2,
//					new OpdfDiscreteFactory<Packet>(Packet.class));
//		
//		hmm.setPi(0, 0.95);
//		hmm.setPi(1, 0.05);
//
//		hmm.setOpdf(0, new OpdfDiscrete<Packet>(Packet.class, 
//				new double[] { 0.95, 0.05 }));
//		hmm.setOpdf(1, new OpdfDiscrete<Packet>(Packet.class,
//				new double[] { 0.20, 0.80 }));
//		
//		hmm.setAij(0, 1, 0.05);
//		hmm.setAij(0, 0, 0.95);
//		hmm.setAij(1, 0, 0.10);
//		hmm.setAij(1, 1, 0.90);
//		
//		return hmm;
//	}
}
