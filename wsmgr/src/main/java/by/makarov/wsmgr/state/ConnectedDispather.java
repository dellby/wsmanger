package by.makarov.wsmgr.state;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import by.makarov.wsmgr.entety.Ping;
import okio.Buffer;

/**
 * Created by Andrey Makarov on 24.04.2016.
 */
public abstract class ConnectedDispather {
    public final long MARK_AS_FAILED_ALARM_MS = 10000L;
    private final long PONG_WAIT_TIME_MS = 10000L;
    //// TODO: 24.04.2016 adding resend failed message
    public final String INTENT_FILTER_PENDING_MESSAGE = "by.makarov.alarm.markmessagesfailed";
    public final String EXTRA_PENDING_MESSAGE = "by.makarov.extra.Message";

    private boolean isConnected;
    private WsContext context;
    long pingId = 0;
    Gson gson;

    PingPongTimer pingIntervalTimer = new PingPongTimer(PingPongTimer.PING);
    PingPongTimer pongWaitTimer = new PingPongTimer(PingPongTimer.PONG);

    public ConnectedDispather(WsContext context) {
        this.context = context;
        gson = new Gson();
    }

    abstract void disconnected();

    abstract void sendCommand(String messageJson);

    class PingPongTimer {
        public static final int PING = 0;
        public static final int PONG = 1;

        private Handler mainHandler = new Handler(Looper.getMainLooper());
        private Runnable current;
        private final int type;

        public PingPongTimer(int type) {
            this.type = type;
        }

        private void onNeedToSendPing() {
            try {
                okio.Buffer payload = new Buffer();
                Ping ping = new Ping("PING", pingId);
                payload.writeUtf8(gson.toJson(ping));
                context.getWebSocket().sendPing(payload);
            } catch (IOException e) {
                onPongTimedOut();
            }
        }

        private void onPongTimedOut() {
            disconnected();
            context.setCurrentState(context.getDisconnected());
            context.disconnected();
        }


        public void cancel() {
            if (current != null) {
            }
            this.mainHandler.removeCallbacks(ConnectedDispather.PingPongTimer.this.current);
        }

        public void start(long periodPing) {
            cancel();
            ConnectedDispather.PingPongTimer.this.current = new Runnable() {
                @Override
                public void run() {
                    switch (ConnectedDispather.PingPongTimer.this.type) {
                        case PING:
                            onNeedToSendPing();
                            pongWaitTimer.start(PONG_WAIT_TIME_MS);
                            break;
                        case PONG:
                            onPongTimedOut();
                            break;
                    }
                }
            };

            this.mainHandler.postDelayed(ConnectedDispather.PingPongTimer.this.current, periodPing);
        }
    }
}
