# **wsmanger**
easy websocket manager based on **okhttp-ws**

to use:
 get wsmgr to you project and install depedency.
 
```
 wsContext = new WsContext(this, "ws://echo.websocket.org");
        wsContext.setOnMessageListener(new WsMessage() {
            @Override
            public void onMessage(String message) {
                
            }
        });
```
   
    wsContext.connect(); // to connect
    wsContext.disconnect(); // to disconnect
    wsContext.sendMessage(string);// send message`
   
        automatic reconnect if the connect failed (wifi/mobile internet changed etc.) is lost in 5 minutes
        automatic ping 10 sec. interval.
        
        exapmle app.. 
        