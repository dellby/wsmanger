package by.makarov.wsmgr.state;

import android.content.Context;

import by.makarov.wsmgr.WsManager;
import by.makarov.wsmgr.WsMessage;
import okhttp3.OkHttpClient;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketListener;

public class WsContext implements State, WsManager {
    private State mConnected;
    private State mDisconnected;
    private State mCurrentState;

    private String mUrl;
    private WebSocket webSocket;
    private Context appContext;
    private WebSocketListener wsListener;
    private OkHttpClient wsClient;
    private WsMessage listenerMessage;
    private boolean isNeedReconnect;

    /**
     * @param appContext context app
     * @param url        url server
     */
    public WsContext(Context appContext, String url) {
        this.mUrl = url;
        this.appContext = appContext;

        mConnected = new WsConnected(this);
        mDisconnected = new WsDiconnected(this);
        mCurrentState = mDisconnected;
    }

    @Override
    public void connected() {
        mCurrentState.connected();
    }

    @Override
    public void disconnected() {
        mCurrentState.disconnected();
    }

    @Override
    public void connecting() {
        mCurrentState.connecting();
    }

    @Override
    public void ping() {
        mCurrentState.ping();
    }

    public State getDisconnected() {
        return mDisconnected;
    }

    public State getCurrentState() {
        return mCurrentState;
    }

    public void setCurrentState(State mCurrentState) {
        this.mCurrentState = mCurrentState;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public Context getAppContext() {
        return appContext;
    }

    public WebSocketListener getWsListener() {
        return wsListener;
    }

    public void setWsListener(WebSocketListener wsListener) {
        this.wsListener = wsListener;
    }

    public OkHttpClient getWsClient() {
        return wsClient;
    }

    public void setWsClient(OkHttpClient wsClient) {
        this.wsClient = wsClient;
    }

    @Override
    public void setOnMessageListener(WsMessage onMessageListener) {
        this.listenerMessage = onMessageListener;
    }

    public boolean isNeedReconnect() {
        return isNeedReconnect;
    }

    public WsMessage getListenerMessage() {
        return listenerMessage;
    }

    @Override
    public void sendMessage(String message) {
        mCurrentState.sendMessage(message);
    }

    @Override
    public void connect() {
        isNeedReconnect = true;
        mCurrentState.connecting();
    }

    @Override
    public void disconnect() {
        isNeedReconnect = false;
        mCurrentState.disconnected();
    }
}
