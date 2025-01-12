package io.ruin.model.entity.npc.actions.barbarianvillage;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.NPCDialogue;

public class TassieSlipcast {

    private static void talkToNpc(Player player, NPC npc) {
        player.dialogue(
                new NPCDialogue(npc.getId(), "Please feel free to use the pottery wheel, I won't be using it <br/>" +
                        "all the time. Put your pots in the kiln when you've made one."),
                new NPCDialogue(npc.getId(), "And make sure you tidy up after yourself!")
        );
    }

    static {
        NPCAction.register("tassie slipcast", "talk-to", TassieSlipcast::talkToNpc);
    }

}
