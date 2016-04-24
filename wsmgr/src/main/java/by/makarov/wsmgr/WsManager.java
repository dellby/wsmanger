package by.makarov.wsmgr;

/**
 * Created by Andrey Makarov on 24.04.2016.
 */
public interface WsManager {
    void connect();

    void disconnect();

    void sendMessage(String message);

    void setOnMessageListener(WsMessage onMessageListener);
}
