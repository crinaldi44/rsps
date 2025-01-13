package io.ruin.model.entity.npc.actions.draynor;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.shop.ShopManager;

public class Fortunato {

    public static final String SHOP_ID = "01ae5c4f-0c5b-4c88-ba23-7bda2c464d1d";

    private static void talkTo(Player player, NPC npc) {
        player.dialogue(
                new NPCDialogue(npc.getId(), "Can I help you at all?"),
                new OptionsDialogue(
                        new Option("Yes, what are you selling?", () -> ShopManager.openIfExists(player, SHOP_ID)),
                        new Option("Not at the moment", () -> player.dialogue(
                                new PlayerDialogue("Not at the moment."),
                                new NPCDialogue(npc.getId(), "Then move along, you filthy ragamuffin, " +
                                        "I have customers to serve!")
                        ))
                )
        );
    }

    static {
        NPCAction.register("fortunato", "talk-to", Fortunato::talkTo);
        NPCAction.register("fortunato", "trade", (player, npc) ->
                ShopManager.openIfExists(player, SHOP_ID));
    }

}
