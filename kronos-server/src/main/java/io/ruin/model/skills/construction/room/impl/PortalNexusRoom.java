package io.ruin.model.skills.construction.room.impl;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.skills.construction.Hotspot;
import io.ruin.model.skills.construction.room.Room;

public class PortalNexusRoom extends Room {

    @Override
    protected void onBuild() {
        getHotspotObjects(Hotspot.PORTAL_NEXUS).forEach(obj -> {
            ObjectAction.register(obj, 1, this::openPortalNexusTeleportMenu);
            ObjectAction.register(obj, 3, this::openPortalNexusConfiguration);
        });
    }

    private void openPortalNexusTeleportMenu(Player player, GameObject gameObject) {
        player.sendMessage("This feature is not yet available.");
    }

    // component $component0, component $component1, component $component2, component $component3, component $component4,
    // component $component5, component $component6, component $component7, component $component8, component $component9,
    // component $component10, component $component11, component $component12, component $component13, component $component14,
    // component $component15, component $component16, component $component17, component $component18, component $component19,
    // component $component20, component $component21, component $component22, int $int23, int $int24, component $component25,
    // component $component26, component $component27

    private void openPortalNexusConfiguration(Player player, GameObject gameObject) {
        if (!player.isInOwnHouse()) {
            player.sendMessage("This is not your portal to configure.");
            return;
        }
        player.openInterface(InterfaceType.MAIN, Interface.PORTAL_NEXUS_CONFIG);
//        player.getPacketSender().sendClientScript(
//                2269,
//                "ccccccccccccccccccccccciiccc",
//
//        );
    }

}
