package io.ruin.model.entity.npc.actions.barbarianvillage;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.shop.ShopManager;

public class Peksa {

    public static final String PEKSA_HELMET_SHOP_UUID = "471a996c-2837-43ec-9f53-4b6eaf12fcbd";

    private static void talkToPeksa(Player player, NPC npc) {
        player.dialogue(
                new NPCDialogue(npc.getId(), "Are you interested in buying or selling a helmet?"),
                new OptionsDialogue(
                        new Option("I could be, yes.", () -> ShopManager.openIfExists(player, PEKSA_HELMET_SHOP_UUID)),
                        new Option("No, I'll pass on that.", () -> player.dialogue(
                                new PlayerDialogue("No, I'll pass on that."),
                                new NPCDialogue(npc.getId(), "Well, come back if you change your mind.")
                        ))
                )
        );
    }

    static {
        NPCAction.register("peksa", "talk-to", Peksa::talkToPeksa);
        NPCAction.register("peksa", "trade", (player, npc) -> ShopManager.openIfExists(player, PEKSA_HELMET_SHOP_UUID));//TODO Fill this in
    }

}
