package io.ruin.model.entity.npc.actions.barbarianvillage;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;

public class GunthorTheBrave {

    private static void talkToGunthorTheBrave(Player player, NPC npc) {

    }

    static {
        NPCAction.register("gunthor the brave", "talk-to", GunthorTheBrave::talkToGunthorTheBrave);
    }

}
