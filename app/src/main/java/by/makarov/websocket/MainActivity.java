package by.makarov.websocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import by.makarov.wsmgr.WsManager;
import by.makarov.wsmgr.WsMessage;
import by.makarov.wsmgr.state.WsContext;

public class MainActivity extends AppCompatActivity {
    private WsManager wsContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wsContext = new WsContext(this, "ws://echo.websocket.org");
        wsContext.setOnMessageListener(new WsMessage() {
            @Override
            public void onMessage(String message) {
                
            }
        });

        wsContext.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wsContext.disconnect();
    }
}
