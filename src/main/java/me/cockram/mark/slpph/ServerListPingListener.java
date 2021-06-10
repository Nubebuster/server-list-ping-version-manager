package me.cockram.mark.slpph;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerListPingListener implements Listener {

    private int minimumProtocolVersion, maximumProtocolVersion;
    private ServerPing.Protocol supportedProtocols;

    public ServerListPingListener(int minimumProtocolVersion, int maximumProtocolVersion, int recommendedProtocol, String supportedVersions) {
        this.minimumProtocolVersion = minimumProtocolVersion;
        this.maximumProtocolVersion = maximumProtocolVersion;
        supportedProtocols = new ServerPing.Protocol(supportedVersions, recommendedProtocol);
    }

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        ServerPing response = event.getResponse();
        if (response == null)
            return;
        int requestVersion = event.getConnection().getVersion();
        if (requestVersion == 32767) {// max signed 16-bit int value
            response.setVersion(supportedProtocols);
        } else if (requestVersion >= minimumProtocolVersion && requestVersion <= maximumProtocolVersion) {
            //protocol name does not matter because it is not displayed if server and client protocols are the same
            response.setVersion(new ServerPing.Protocol("", requestVersion));
        } else {
            response.setVersion(supportedProtocols);
        }
        event.setResponse(response);
    }
}
