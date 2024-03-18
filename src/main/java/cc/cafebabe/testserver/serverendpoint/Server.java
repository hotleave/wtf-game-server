package cc.cafebabe.testserver.serverendpoint;

import cc.cafebabe.testserver.entity.Channel;
import cc.cafebabe.testserver.entity.Player;
import cc.cafebabe.testserver.util.Util;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@ServerEndpoint("/server")
@Component
@Slf4j
public class Server {
    public static String version = "0.5.0";
    public static boolean running = true;


    static {
        Properties pps = System.getProperties();
        String ver = pps.getProperty("WTFGameVersion");
        if (ver != null) {
            version = ver;
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        try {
            log.info("session create: {}", session.getId());
            TaskControlCenter.addTask(new Task(session, TaskType.OPEN, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        try {
            log.info("session closed: {}", session.getId());
            TaskControlCenter.addTask(new Task(session, TaskType.CLOSE, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            if (message == null || message.trim().length() <= 0) return;
            String decodedMessage = "";
            decodedMessage = Util.decode(message);
            if (decodedMessage == null || decodedMessage.trim().length() <= 0) return;
            TaskControlCenter.addTask(new Task(session, TaskType.MESSAGE, decodedMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        running = false;
        try {
            Channel.broadcastToAll(Pack.buildKeyPack("$shutdown"));
            for (Channel c : Channel.channels.values()) {
                Set<Player> players = new HashSet<Player>();
                players.addAll(c.getPlayers());
                for (Player p : players) {
                    p.logout();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}