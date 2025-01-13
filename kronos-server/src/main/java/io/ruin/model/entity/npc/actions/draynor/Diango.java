package io.ruin.model.entity.npc.actions.draynor;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.ActionDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.shop.ShopManager;

public class Diango {

    public static final String SHOP_ID = "12def81a-82c2-430b-bac0-64fafef735d8";

    private static void talkTo(Player player, NPC npc) {
        int DIANGO = npc.getId();
        player.dialogue(
                new NPCDialogue(npc.getId(), "Howdy there partner! Want to see my spinning plates? Or did ya want a holiday item back?"),
                new OptionsDialogue(
                        new Option("Spinning plates?", () -> {
                            player.dialogue(
                                    new PlayerDialogue("Spinning plates?"),
                                    new NPCDialogue(DIANGO, "That's right. There's a funny story behind " +
                                            "them, their shipment was held up by thieves."),
                                    new NPCDialogue(DIANGO, "The crate was marked 'Dragon Plates'. Apparently " +
                                            "they thought it was some kind of armour, when really it's just a " +
                                            "plate with a dragon on it!"),
                                    new ActionDialogue(() -> {
                                        ShopManager.openIfExists(player, SHOP_ID);
                                    })
                            );
                        }),
                        new Option("I'd like to check holiday items, please.", () -> {
                            player.dialogue(
                                    new PlayerDialogue("I'd like to check holiday items, please."),
                                    new ActionDialogue(() -> holidayItems(player, npc))
                            );
                        }),
                        new Option("What else are you selling?", () -> {
                            player.dialogue(
                                    new PlayerDialogue("What else are you selling?"),
                                    new ActionDialogue(() -> {
                                        ShopManager.openIfExists(player, SHOP_ID);
                                    })
                            );
                        }),
                        new Option("I'm fine, thanks.", () -> player.dialogue(
                                new PlayerDialogue("I'm fine, thanks.")
                        ))
                )
        );
    }

    private static void redeemCode(Player player, NPC npc) {
        player.stringInput("Please enter your code.", (code) -> {
            // TODO.
            player.sendMessage("That code was not recognized.");
        });
    }

    private static void holidayItems(Player player, NPC npc) {
        player.dialogue(
                new NPCDialogue(npc.getId(), "It looks as if you already have everything!")
        );
    }

    static {
        NPCAction.register("diango", "talk-to", Diango::talkTo);
        NPCAction.register("diango", "holiday-items", Diango::holidayItems);
        NPCAction.register("diango", "redeem-code", Diango::redeemCode);
        NPCAction.register("diango", "trade", (player, npc) ->
                ShopManager.openIfExists(player, SHOP_ID));

    }

}
