package io.ruin.model.entity.npc.actions.draynor;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;

import static io.ruin.cache.ItemID.QUEST_POINT_CAPE;

public class Morgan {

    public static void talkTo(Player player, NPC npc) {
        int MORGAN = npc.getId();
        player.dialogue(
                new NPCDialogue(MORGAN, "Please please help us, bold adventurer!"),
                new PlayerDialogue("What's the problem?"),
                new NPCDialogue(MORGAN, "Our little village has been dreadfully ravaged by an evil vampyre! " +
                        "He lives in the basement of the manor to the north, we need someone to get rid of him " +
                        "once and for all!"),
                new NPCDialogue(MORGAN, "Oh, but another time I'm afraid..."),
                new ItemDialogue().one(QUEST_POINT_CAPE, "This quest is currently unavailable. " +
                        "Check back later!")
        );
    }

    static {
        NPCAction.register("morgan", "talk-to", Morgan::talkTo);
    }

}
