package by.makarov.wsmgr.state;

/**
 * Created by Andrey Makarov on 24.04.2016.
 */
public interface State {

    void connected();

    void disconnected();

    void connecting();

    void ping();

    void sendMessage(String message);
}
