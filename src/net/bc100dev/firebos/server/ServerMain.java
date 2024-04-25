package net.bc100dev.firebos.server;

import java.io.File;

public class ServerMain {

    public static void main(String[] args) {
        File srvDefaultFile = new File("/etc/net.bc100dev/FireBOS/Server.json");
        if (!srvDefaultFile.exists())
            throw new RuntimeException("\"/etc/net.bc100dev/FireBOS/Server.json\" does not exist");

        if (!srvDefaultFile.canRead())
            throw new RuntimeException("\"/etc/net.bc100dev/FireBOS/Server.json\" is not readable");
    }

}
