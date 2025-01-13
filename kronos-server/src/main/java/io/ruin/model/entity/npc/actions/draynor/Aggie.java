package io.ruin.model.entity.npc.actions.draynor;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.*;
import io.ruin.model.inter.utils.Option;

import static io.ruin.cache.ItemID.COINS_995;
import static io.ruin.cache.ItemID.REDBERRIES;
import static io.ruin.cache.ItemID.ONION;
import static io.ruin.cache.ItemID.RED_DYE;
import static io.ruin.cache.ItemID.YELLOW_DYE;
import static io.ruin.cache.ItemID.BLUE_DYE;
import static io.ruin.cache.ItemID.WOAD_LEAF;
import static io.ruin.cache.ItemID.POT_OF_FLOUR;

public class Aggie {

    private static Option[] getOtherColorsOptions(int npcId) {
        return new Option[] {
                new Option("What other colours can you make?", (player) -> {
                    player.dialogue(getDyeOptionsDialogue(npcId));
                }),
                new Option("Thanks", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Thanks."),
                            new NPCDialogue(npcId, "You're welcome!")
                    );
                })
        };
    }

    private static OptionsDialogue getRedDyeOptionsDialogue(int npcId) {
        Option[] otherColorsOptionsDialogue = getOtherColorsOptions(npcId);
        return new OptionsDialogue(
                new Option("Okay, make me some red dye please.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Okay, make me some red dye please."),
                            new ActionDialogue(() -> {
                                if (
                                                player.getInventory().contains(REDBERRIES, 3) &&
                                                player.getInventory().contains(COINS_995, 5)
                                ) {
                                    player.getInventory().remove(COINS_995, 5);
                                    player.getInventory().remove(REDBERRIES, 3);
                                    player.getInventory().add(RED_DYE);
                                } else {
                                    player.sendMessage("You need 3 redberries and 5 coins.");
                                }
                            })
                    );
                }),
                new Option("I don't think I have all the ingredients yet.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Okay, make me some red dye please."),
                            new NPCDialogue(npcId, "You know what you need to get, now come back when you " +
                                    "have them. Goodbye for now.")
                    );
                }),
                new Option("I can do without dye at that price.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("I can do without dye at that price."),
                            new NPCDialogue(npcId, "That's your choice, but I would think you have killed " +
                                    "for less. I can see it in your eyes.")
                    );
                }),
                new Option("Where do I get redberries?", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Where do I get redberries?"),
                            new NPCDialogue(npcId, "I pick mine from the woods south of Varrock. The food " +
                                    "shop in Port Sarim sometimes has some as well."),
                            new OptionsDialogue(otherColorsOptionsDialogue)
                    );
                }),
                otherColorsOptionsDialogue[0],
                otherColorsOptionsDialogue[1]
        );
    }

    private static OptionsDialogue getYellowDyeOptionsDialogue(int npcId) {
        Option[] otherColorsOptionsDialogue = getOtherColorsOptions(npcId);
        return new OptionsDialogue(
                new Option("Okay, make me some yellow dye please.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Yellow is a strange colour to get, comes from onion skins. I need 2 " +
                                    "onions and 5 coins to make yellow dye."),
                            new ActionDialogue(() -> {
                                if (
                                        player.getInventory().contains(ONION, 2) &&
                                                player.getInventory().contains(COINS_995, 5)
                                ) {
                                    player.getInventory().remove(COINS_995, 5);
                                    player.getInventory().remove(ONION, 2);
                                    player.getInventory().add(YELLOW_DYE);
                                } else {
                                    player.sendMessage("You need 2 onions and 5 coins.");
                                }
                            })
                    );
                }),
                new Option("I don't think I have all the ingredients yet.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Okay, make me some red dye please."),
                            new NPCDialogue(npcId, "You know what you need to get, now come back when you " +
                                    "have them. Goodbye for now.")
                    );
                }),
                new Option("I can do without dye at that price.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("I can do without dye at that price."),
                            new NPCDialogue(npcId, "That's your choice, but I would think you have killed " +
                                    "for less. I can see it in your eyes.")
                    );
                }),
                new Option("Where do I get onions?", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Where do I get onions?"),
                            new NPCDialogue(npcId, "There are some onions growing on a farm to the East " +
                                    "of here, next to the sheep field. "),
                            new OptionsDialogue(otherColorsOptionsDialogue)
                    );
                }),
                otherColorsOptionsDialogue[0],
                otherColorsOptionsDialogue[1]
        );
    }

    private static OptionsDialogue getBlueDyeOptionsDialogue(int npcId) {
        Option[] otherColorsOptionsDialogue = getOtherColorsOptions(npcId);
        return new OptionsDialogue(
                new Option("Okay, make me some blue dye please.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("2 woad leaves and 5 coins to you."),
                            new ActionDialogue(() -> {
                                if (
                                        player.getInventory().contains(WOAD_LEAF, 2) &&
                                        player.getInventory().contains(COINS_995, 5)
                                ) {
                                    player.getInventory().remove(COINS_995, 5);
                                    player.getInventory().remove(WOAD_LEAF, 3);
                                    player.getInventory().add(BLUE_DYE);
                                } else {
                                    player.sendMessage("You need 2 woad leaves and 5 coins.");
                                }
                            })
                    );
                }),
                new Option("I don't think I have all the ingredients yet.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Okay, make me some red dye please."),
                            new NPCDialogue(npcId, "You know what you need to get, now come back when you " +
                                    "have them. Goodbye for now.")
                    );
                }),
                new Option("I can do without dye at that price.", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("I can do without dye at that price."),
                            new NPCDialogue(npcId, "That's your choice, but I would think you have killed " +
                                    "for less. I can see it in your eyes.")
                    );
                }),
                new Option("Where do I get woad leaves?", (player) -> {
                    player.dialogue(
                            new PlayerDialogue("Where do I get woad leaves?"),
                            new NPCDialogue(npcId, "Woad leaves are fairly hard to find. My other customers " +
                                    "tell me the chief gardener in Falador grows them."),
                            new OptionsDialogue(otherColorsOptionsDialogue)
                    );
                }),
                otherColorsOptionsDialogue[0],
                otherColorsOptionsDialogue[1]
        );
    }

    private static OptionsDialogue getDyeOptionsDialogue(int npcId) {
            return new OptionsDialogue(
                    new Option("What do you need to make red dye?", (player) -> {
                        player.dialogue(
                                new PlayerDialogue("What do you need to make red dye?"),
                                new NPCDialogue(npcId, "3 lots of redberries and 5 coins to you."),
                                getRedDyeOptionsDialogue(npcId)
                        );
                    }),
                    new Option("What do you need to make yellow dye?", (player) -> {
                        player.dialogue(
                                new PlayerDialogue("What do you need to make yellow dye?"),
                                new NPCDialogue(npcId, "3 lots of redberries and 5 coins to you."),
                                getYellowDyeOptionsDialogue(npcId)
                        );
                    }),
                    new Option("What do you need to make blue dye?", (player) -> {
                        player.dialogue(
                                new PlayerDialogue("What do you need to make blue dye?"),
                                new NPCDialogue(npcId, "3 lots of redberries and 5 coins to you."),
                                getBlueDyeOptionsDialogue(npcId)
                        );
                    }),
                    new Option("No thanks, I am happy the colour I am.", (player) -> {
                        player.dialogue(
                                new PlayerDialogue("No thanks, I am happy the colour I am."),
                                new NPCDialogue(npcId, "You are easily pleased with yourself then. " +
                                        "When you need dyes, come to me.")
                        );
                    })
            );
    }

    private static void talkToAggie(Player player, NPC npc) {
        OptionsDialogue dyeOptionsDialogue = getDyeOptionsDialogue(npc.getId());
        player.dialogue(
                new NPCDialogue(npc.getId(), "What can I help you with?"),
                new OptionsDialogue(
                        new Option("What could you make for me?", () -> {
                            player.dialogue(
                                    new PlayerDialogue("What could you make for me?"),
                                    new NPCDialogue(npc.getId(), "I mostly just make what I find pretty. I " +
                                            "sometimes make dye for the women's clothes to brighten the place up. I " +
                                            "can make red, yellow and blue dyes. If you'd like some, just bring me " +
                                            "the appropriate ingredients."),
                                    dyeOptionsDialogue
                            );
                        }),
                        new Option("Cool, do you turn people into frogs?", () -> {
                            player.dialogue(
                                    new PlayerDialogue("Cool, do you turn people into frogs?"),
                                    new NPCDialogue(npc.getId(), "Oh, not for years, but if you meet a talking chicken, " +
                                            "you have probably met the professor in the manor north of here. " +
                                            "A few years ago it was flying fish. That machine is a menace.")
                            );
                        }),
                        new Option("You mad old witch, you can't help me.", () -> {
                            Dialogue foundItems = new NPCDialogue(npc.getId(), "Oh, you like to call a witch names do you?");
                            Dialogue endDialogue =
                                    new NPCDialogue(npc.getId(), "You should be careful about insulting " +
                                            "a witch. You never know what shape you could wake up in.");
                            Dialogue fineDialogue = new NPCDialogue(npc.getId(), "That's a fine for " +
                                    "insulting a witch. You should learn some respect.");
                            player.dialogue(
                                    new PlayerDialogue("You mad old witch, you can't help me."),
                                    new ActionDialogue(() -> {
                                        if (player.getInventory().contains(COINS_995, 101)) {
                                            player.dialogue(
                                                    foundItems,
                                                    new ActionDialogue(() -> {
                                                        player.dialogue(
                                                                new ItemDialogue().one(COINS_995, "Aggie waves her hands about, and you seem to be 20 coins poorer."),
                                                                fineDialogue,
                                                                endDialogue
                                                        );
                                                        player.getInventory().remove(COINS_995, 20);
                                                    })
                                            );
                                        } else if (player.getInventory().contains(COINS_995, 100)) {
                                            player.dialogue(
                                                    foundItems,
                                                    new ActionDialogue(() -> {
                                                        player.dialogue(
                                                                new ItemDialogue().one(COINS_995, "Aggie waves her hands about, and you seem to be five coins poorer."),
                                                                fineDialogue,
                                                                endDialogue
                                                        );
                                                        player.getInventory().remove(COINS_995, 5);
                                                    })
                                            );
                                        } else if (player.getInventory().contains(POT_OF_FLOUR)) {
                                            player.dialogue(
                                                    foundItems,
                                                    new ActionDialogue(() -> {
                                                        player.dialogue(
                                                                new ItemDialogue().one(POT_OF_FLOUR, "Aggie waves her hands about, and you seem to have a pot of flour less."),
                                                                fineDialogue,
                                                                endDialogue
                                                        );
                                                        player.getInventory().remove(POT_OF_FLOUR, 1);
                                                    })
                                            );
                                        }
                                    })
                            );
                        }),
                        new Option("Can you make dyes for me please?", () -> {
                            player.dialogue(
                                    new PlayerDialogue("Can you make dyes for me please?"),
                                    new NPCDialogue(npc.getId(), "What sort of dye would you like? Red, yellow or blue?"),
                                    dyeOptionsDialogue
                            );
                        })
                )
        );
    }

    static {
        NPCAction.register("aggie", "talk-to", Aggie::talkToAggie);
    }

}
