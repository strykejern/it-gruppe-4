package gui;

/**
 *
 * @author Anders
 */
public class UpdaterThread extends Thread {
    private GUIUpdater listener;
    private int updateInterval;
    private boolean running;

    public UpdaterThread(GUIUpdater listener, int updateInterval) {
        this.listener = listener;
        this.updateInterval = updateInterval;
    }

    @Override
    public void run(){
        running = true;
        while (running){
            long time = System.currentTimeMillis();

            listener.updateGUI();

            try {
                Thread.sleep(
                        updateInterval - (System.currentTimeMillis() - time));
            }
            catch (InterruptedException e){

            }
        }
    }

    public void end(){
        running = false;
    }
}
