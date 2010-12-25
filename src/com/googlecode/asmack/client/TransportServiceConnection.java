package com.googlecode.asmack.client;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.googlecode.asmack.connection.IXmppTransportService;

/**
 * A service connection implementation for the xmpp transport service.
 */
class TransportServiceConnection implements ServiceConnection {

    /**
     * The client that will be called on connection events.
     */
    private final AsmackClient client;

    /**
     * @param asmackClient
     */
    TransportServiceConnection(AsmackClient asmackClient) {
        client = asmackClient;
    }

    /**
     * The internal service.
     */
    private IXmppTransportService xmppTransportService;

    /**
     * Called when the service is connected. Calls the clients service connect.
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        xmppTransportService =
            IXmppTransportService.Stub.asInterface(service);
        client.onTrasportServiceConnect(xmppTransportService);
    }

    /**
     * Called when the service disconnects. Calls the client service
     * disconnect.
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        client.onTrasportServiceDisconnect(xmppTransportService);
    }

}
