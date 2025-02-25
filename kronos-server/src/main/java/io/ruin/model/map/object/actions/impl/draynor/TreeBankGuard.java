package io.ruin.model.map.object.actions.impl.draynor;

import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;

import static io.ruin.cache.NpcID.BANK_GUARD;
import static io.ruin.cache.ObjectID.TREE_10041;

public class TreeBankGuard {

    static {
        ObjectAction.register(TREE_10041, "talk to", TreeBankGuard::talkTo);
    }

    private static void talkTo(Player player, GameObject gameObject) {
        player.dialogue(
                new PlayerDialogue("Hello?"),
                new NPCDialogue(BANK_GUARD, "Ssshhh! What do you want?"),
                new PlayerDialogue("Well, it's not every day you see a man up a tree."),
                new NPCDialogue(BANK_GUARD, "I'm trying to observe a suspect. Leave me alone!"),
                new OptionsDialogue(
                        new Option("This is about the bank robbery, right?", () -> {
                            player.dialogue(
                                    new PlayerDialogue("This is about the bank robbery, right?"),
                                    new NPCDialogue(BANK_GUARD, "Yes, that's right. We're keeping the " +
                                            "suspect under tight observation for the moment."),
                                    new PlayerDialogue("Can't you just... I dunno... arrest him?"),
                                    new NPCDialogue(BANK_GUARD, "I'm not meant to discuss the case. " +
                                            "You know what confidentiality rules are like."),
                                    new PlayerDialogue("Fair enough.")
                            );
                        }),
                        new Option("You're not being very subtle up there.", () -> {
                            player.dialogue(
                                    new PlayerDialogue("You're not being very subtle up there."),
                                    new NPCDialogue(BANK_GUARD, "I'd be doing a lot better if nits like you didn't come " +
                                            "crowding around me all day!"),
                                    new PlayerDialogue("But your legs are hanging down!"),
                                    new NPCDialogue(BANK_GUARD, "Go away!"),
                                    new PlayerDialogue("Please yourself!")
                            );
                        }),
                        new Option("Can I do anything to help?", () -> {
                            player.dialogue(
                                    new PlayerDialogue("Can I do anything to help?"),
                                    new NPCDialogue(BANK_GUARD, "That's very kind of you. I'd rather like " +
                                            "a nice bowl of stew, if you could fetch me one. I don't get many " +
                                            "meal breaks."),
                                    new PlayerDialogue("You want a bowl of stew?"),
                                    new NPCDialogue(BANK_GUARD, "If you wouldn't mind..."),
                                    new PlayerDialogue("I'll think about it!")
                            );
                        })
                )
        );
    }

}
