package gui;

/**
 * A Thread designed to run in the background and update the gui of something
 * that implements the GUIUpdater interface
 *
 * @author Anders
 */
public class UpdaterThread extends Thread {
    private GUIUpdater listener;
    private int updateInterval;
    private boolean running;

    /**
     * Constructs the thread
     *
     * @param listener The class that implemented GUIUpdater
     * @param updateInterval The interval between the updates (in ms)
     */
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
                Thread.sleep(Math.max(0,
                        updateInterval - (System.currentTimeMillis() - time)));
            }
            catch (InterruptedException e){

            }
        }
    }

    /**
     * Manually force the thread to update the gui
     */
    public void manualUpdate(){
        this.interrupt();
    }

    /**
     * End the Thread's main loop
     */
    public void end(){
        running = false;
    }
}
