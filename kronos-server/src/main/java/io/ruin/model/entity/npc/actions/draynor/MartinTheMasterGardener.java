package io.ruin.model.entity.npc.actions.draynor;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.*;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.stat.StatRequirement;
import io.ruin.model.stat.StatType;

import static io.ruin.cache.ItemID.QUEST_POINT_CAPE;
import static io.ruin.cache.ItemID.COINS_995;
import static io.ruin.cache.ItemID.FARMING_CAPE;
import static io.ruin.cache.ItemID.FARMING_HOOD;

/**
 * TODO: This should check the player's members-only status & handle quest dialogue/states.
 * @author Thanos
 */
public class MartinTheMasterGardener {

    private static void talkTo(Player player, NPC npc) {
        int MARTIN_THE_MASTER_GARDENER = npc.getId();
        player.dialogue(
                new OptionsDialogue(
                        new Option("Ask about the Skillcape of Farming.", () -> {
                            player.dialogue(
                                    new ActionDialogue(() -> {
                                        if (player.getStats().check(
                                                new StatRequirement(StatType.Farming, 99)
                                        )) {
                                            player.dialogue(
                                                    new PlayerDialogue("Can I buy a Skillcape of Farming from you?"),
                                                    new NPCDialogue(MARTIN_THE_MASTER_GARDENER, "Of course, fellow farmer. If you wear this " +
                                                            "cape you'll receive increased yields from your herbs. " +
                                                            "That'll be 99000 coins."),
                                                    new OptionsDialogue(
                                                            new Option("I'm not paying that!", () -> {
                                                                player.dialogue(
                                                                        new PlayerDialogue("I'm not paying that!")
                                                                );
                                                            }),
                                                            new Option("Sure, not many people own one.", () -> {
                                                                player.dialogue(
                                                                        new PlayerDialogue("Sure, not many people " +
                                                                                "own one."),
                                                                        new ActionDialogue(() -> {
                                                                            if (player.getInventory().contains(
                                                                                    COINS_995,
                                                                                    99000
                                                                            )) {
                                                                                if (!player.getInventory().hasFreeSlots(2)) {
                                                                                    player.dialogue(new NPCDialogue(MARTIN_THE_MASTER_GARDENER, "Unfortunately all Skillcapes are only available with a free hood, it's part of a skill promotion deal; buy one get one free, you know. So you'll need to free up some inventory space before I can sell you one."));
                                                                                } else {
                                                                                    player.dialogue(new NPCDialogue(MARTIN_THE_MASTER_GARDENER, "That's true; us Master Farmers are a unique breed."));
                                                                                    player.getInventory().remove(COINS_995, 99000);
                                                                                    player.getInventory().add(FARMING_CAPE);
                                                                                    player.getInventory().add(FARMING_HOOD);
                                                                                }
                                                                            } else {
                                                                                player.dialogue(
                                                                                        new PlayerDialogue("But, " +
                                                                                                "unfortunately, I " +
                                                                                                "don't have enough " +
                                                                                                "money with me."),
                                                                                        new NPCDialogue(MARTIN_THE_MASTER_GARDENER,
                                                                                                "Well, come " +
                                                                                                        "back and see " +
                                                                                                        "me when you do.")
                                                                                );
                                                                            }
                                                                        })
                                                                );
                                                            })
                                                    )

                                            );
                                        } else {
                                            player.dialogue(

                                            );
                                        }
                                    })
                            );
                        }),
                        new Option("Ask about the quest.", () -> {
                            player.dialogue(
                                    new PlayerDialogue("Hello."),
                                    new NPCDialogue(MARTIN_THE_MASTER_GARDENER,
                                            "I can't chat right now, I have too many things to worry about."),
                                    new ActionDialogue(() -> {
                                        player.sendMessage("You do not meet the requirements to start the Fairytale I - Growing Pains quest.");
                                        player.dialogue(
                                                new ItemDialogue().one(QUEST_POINT_CAPE, "This quest is currently unavailable. Check back later!")
                                        );
                                    })
                            );
                        })
                )
        );
    }

    static {
        NPCAction.register(
                "martin the master gardener",
                "talk-to",
                MartinTheMasterGardener::talkTo
        );
    }

}
