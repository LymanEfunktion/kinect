package de.rocovomo.osgi.gesture.resize.window.handler;

import java.util.HashSet;
import java.util.Set;

import javax.management.Notification;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.core.KinectManager;
import org.jnect.core.SpeechListener;

public class OpenHandler {

    private static final String STOP_RESIZE = "Stop Resize";
    private static final String RESIZE = "Enable Resize";
    private HumanContainer skeletonModel;

    public class LeftHandAdapter extends AdapterImpl implements Adapter {

            private final MWindow window;

            public LeftHandAdapter(MWindow window) {
                    this.window = window;
            }

            public void notifyChanged(Notification msg) {
                    window.setX((int) ((((PositionedElement)msg.getNotifier()).getX()+1)*500));
                    window.setY((int) (((((PositionedElement)msg.getNotifier()).getY()*-1)+1)*200));
                    super.notifyChanged(msg);
            }

    }
    public class RightHandAdapter extends AdapterImpl implements Adapter {
            private final MWindow window;

            public RightHandAdapter(MWindow window) {
                    this.window = window;
            }

            public void notifyChanged(Notification msg) {
                    window.setWidth((int) ((skeletonModel.getRightsHand().getX()-skeletonModel.getLeftHand().getX())*500));
                    window.setHeight(-1*(int) ((skeletonModel.getRightsHand().getY()-skeletonModel.getLeftHand().getY())*500));
                    super.notifyChanged(msg);
            }
    }

    @Execute
    public void execute(final MWindow window){
            final KinectManager kinectManager = KinectManager.INSTANCE;
            kinectManager.startKinect();
            
            final Adapter leftHandAdapter = new  LeftHandAdapter(window);
            final Adapter rightHandAdapter = new RightHandAdapter(window);
            
            kinectManager.addSpeechListener(new SpeechListener() {
                    

                    @Override
                    public void notifySpeech(String speech) {
                            if(speech.equals(RESIZE)){
                                    resize();
                            }
                            if(speech.equals(STOP_RESIZE)){
                                    stopresize();
                            }
                            
                    }
                    
                    private void stopresize() {
                            kinectManager.getSkeletonModel().getLeftHand().eAdapters().remove(leftHandAdapter );
                            kinectManager.getSkeletonModel().getRightHand().eAdapters().remove(rightHandAdapter );
                            kinectManager.stopSkeletonTracking();
                            
                    }

                    private void resize() {
                            kinectManager.startSkeletonTracking();
                            skeletonModel=kinectManager.getSkeletonModel();
                            kinectManager.getSkeletonModel().getLeftHand().eAdapters().add(leftHandAdapter );
                            kinectManager.getSkeletonModel().getRightHand().eAdapters().add(rightHandAdapter );
                            
                    }

                    @Override
                    public Set<String> getWords() {
                            Set<String> ret = new HashSet<String>();
                            ret.add(RESIZE);
                            ret.add(STOP_RESIZE);
                            return ret;
                    }
            });
            
            kinectManager.startSpeechRecognition(); 
    }

}
