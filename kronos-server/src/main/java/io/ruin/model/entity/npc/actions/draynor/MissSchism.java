package io.ruin.model.entity.npc.actions.draynor;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.*;
import io.ruin.model.inter.utils.Option;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MissSchism {

    private static List<Dialogue> wiseOldManDialogue(Player player, int npcId) {
        return Arrays.asList(
                new PlayerDialogue("Well, I know of him."),
                new NPCDialogue(npcId, "When he first moved here, he didn't bring much. From the window you could see he just had some old furniture and a few dusty ornaments."),
                new NPCDialogue(npcId, "Here, look at this picture:"),
                // Cutscene of a poor Wise Old Man is shown
                new NPCDialogue(npcId, "Also he always seemed so poor. When I went round to collect donations for the Draynor Manor Restoration Fund, he couldn't spare them a penny!"),
                new PlayerDialogue("So he's redecorated?"),
                new NPCDialogue(npcId, "Well, just you look in there now!"),
                // Cutscene of a rich Wise Old Man is shown
                new NPCDialogue(npcId, "You see? It's full of jewellery and decorations! And all those expensive things appeared just after the bank got robbed."),
                new NPCDialogue(npcId, "He changed his hat too - he used to wear a scruffy old black thing, but suddenly he was wearing that party hat!"),
                new PlayerDialogue("So that's why you're telling people he was the bank robber?"),
                new NPCDialogue(npcId, "Oooh, my dear, I'm SURE of it! I went upstairs in his house once, while he was out walking, and do you know what I found?"),
                new PlayerDialogue("A sign saying 'Trespassers will be prosecuted'?"),
                new NPCDialogue(npcId, "No, it was a telescope! It was pointing right at the bank! He was spying on the bankers, planning the big robbery!"),
                new NPCDialogue(npcId, "I bet if you go and look through it now, you'll find it's pointing somewhere different now he's finished with the bank."),
                new PlayerDialogue("I'd like to go now."),
                new NPCDialogue(npcId, "Oh, really? Well, do keep an eye on him - I just KNOW he's planning something...")
        );
    }

    private static void talkTo(Player player, NPC npc) {
        int MISS_SCHISM = npc.getId();
        player.dialogue(
                new NPCDialogue(MISS_SCHISM, "Oooh, my dear, have you heard the news?"),
                new OptionsDialogue(
                        new Option("Ok, tell me about the news.", () -> {
                            List<Dialogue> wiseOldManDialogue = wiseOldManDialogue(player, MISS_SCHISM);
                            player.dialogue(
                                    new PlayerDialogue("Ok, tell me about the news."),
                                    new NPCDialogue(MISS_SCHISM, "It's terrible, absolutely terrible! Those poor people!"),
                                    new PlayerDialogue("Ok, yeah."),
                                    new NPCDialogue(MISS_SCHISM, "And who'd have ever thought such a sweet old gentleman would do such a thing?"),
                                    new PlayerDialogue("Are we talking about the bank robbery?"),
                                    new NPCDialogue(MISS_SCHISM, "Oh yes, my dear. It was terrible! TERRIBLE!"),
                                    new NPCDialogue(MISS_SCHISM, "But tell me - have you been around here before, or are you new to these parts?"),
                                    new OptionsDialogue(
                                            new Option("I'm quite new.", () -> {
                                                List<Dialogue> dialogues = Arrays.asList(
                                                        new PlayerDialogue("I'm quite new."),
                                                        new NPCDialogue(MISS_SCHISM, "Aah, perhaps you missed the excitement. It's that old man in this house here. Do you know him?")
                                                );
                                                dialogues.addAll(wiseOldManDialogue);
                                                player.dialogue(dialogues.toArray(new Dialogue[0]));
                                            }),
                                            new Option("I've been around here for ages.", () -> {
                                                List<Dialogue> dialogues = Arrays.asList(
                                                        new PlayerDialogue("I've been around here for ages."),
                                                        new NPCDialogue(MISS_SCHISM, "Ah, so you'd have seen the changes here. It's that old man in this house here. Do you know him?")
                                                );
                                                dialogues.addAll(wiseOldManDialogue);
                                                player.dialogue(dialogues.toArray(new Dialogue[0]));
                                            }),
                                            new Option("I've had enough of talking to you.", () -> {
                                                player.dialogue(
                                                        new PlayerDialogue("I've had enough of talking to you."),
                                                        new NPCDialogue(MISS_SCHISM, "Maybe another time, my dear.")
                                                );
                                            })
                                    )
                            );
                        }),
                        new Option("Who are you?", () -> {
                            player.dialogue(
                                    new PlayerDialogue("Who are you?"),
                                    new NPCDialogue(MISS_SCHISM, "I, my dear, am a concerned citizen of Draynor Village. Ever since the Council allowed those farmers to set up their stalls here, we've had a constant flow of thieves and murderers through our fair village, and I decided"),
                                    new NPCDialogue(MISS_SCHISM, "that someone HAD to stand up and keep an eye on the situation."),
                                    new NPCDialogue(MISS_SCHISM, "I also do voluntary work for the Draynor Manor Restoration Fund. We're campaigning to have Draynor Manor turned into a museum before the wet-rot destroys it completely."),
                                    new PlayerDialogue("Right...")
                            );
                        }),
                        new Option("I'm not talking to you, you horrible woman.", () -> {
                            player.dialogue(
                                    new PlayerDialogue("I'm not talking to you, you horrible woman."),
                                    new NPCDialogue(MISS_SCHISM, "Oooh.")
                            );
                        })
                )
        );
    }

    static {
        NPCAction.register("miss schism", "talk-to", MissSchism::talkTo);
    }

}
