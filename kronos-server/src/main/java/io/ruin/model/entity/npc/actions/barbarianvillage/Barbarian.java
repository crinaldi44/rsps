package io.ruin.model.entity.npc.actions.barbarianvillage;

import io.ruin.api.utils.ArrayUtils;
import io.ruin.api.utils.Random;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.SpawnListener;
import io.ruin.model.inter.dialogue.ActionDialogue;
import io.ruin.model.inter.dialogue.Dialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;

public class Barbarian {

    private static void attackPlayer(Player player, NPC npc) {
        npc.forceText("YEEEEEEEEAARRRGGGGHHHHHHHH");
        npc.face(player);
        npc.getCombat().setTarget(player);
    }

    public static void talkToBarbarian(
            Player player,
            NPC npc
    ) {

        final int npcId = npc.getId();

        final Dialogue[] possibleInitialDialogues = new Dialogue[] {
                new NPCDialogue(npcId, "Wanna fight?"),
                new NPCDialogue(npcId, "Ah, you come for fight, ja?"),
                new NPCDialogue(npcId, "You look funny."),
                new NPCDialogue(npcId, "Grrr!"),
                new NPCDialogue(npcId, "What you want?"),
                new NPCDialogue(npcId, "Go away!"),

        };

        npc.face(player);
        player.dialogue(
                Random.get(possibleInitialDialogues),
                new ActionDialogue(() -> attackPlayer(player, npc))
        );
    }

    static {
        SpawnListener.register(ArrayUtils.of("barbarian"), npc -> {
            npc.addEvent(e -> {
                while(true) {
                    e.delay(Random.get(200, 800));
                    npc.forceText("YEEEEEEEEAARRRGGGGHHHHHHHH");
                }
            });
        });
        NPCAction.register("barbarian", "talk-to", Barbarian::talkToBarbarian);
    }
}