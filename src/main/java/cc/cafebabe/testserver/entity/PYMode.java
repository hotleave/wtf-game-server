package cc.cafebabe.testserver.entity;

import cc.cafebabe.testserver.serverendpoint.Pack;
import com.fasterxml.jackson.databind.JsonNode;

public class PYMode extends GameMode {

    public PYMode(Channel channel) {
        super(channel);
    }

    @Override
    public String getModeCode() {
        return "py";
    }

    @Override
    public void run() {

    }

    @Override
    public void stopPlay() {

    }

    @Override
    public void onJoin(Player player) {

    }

    @Override
    public void onLeave(Player player) {

    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public int getMap() {
        return 0;
    }

    @Override
    public Object getMapData() {
        return null;
    }

    @Override
    public void handleMovePack(Player player, JsonNode map) {
        player.getGameData().setPosX(map.get("x").asInt());
        player.getGameData().setPosY(map.get("y").asInt());
        player.getChannel().broadcast(Pack.buildKVPack("$move", map));
    }

}
