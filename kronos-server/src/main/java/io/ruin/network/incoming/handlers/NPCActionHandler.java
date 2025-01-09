package io.ruin.network.incoming.handlers;

import io.ruin.api.buffer.InBuffer;
import io.ruin.cache.NPCDef;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.Dialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.route.routes.TargetRoute;
import io.ruin.network.incoming.Incoming;
import io.ruin.utility.DebugMessage;
import io.ruin.utility.IdHolder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

@IdHolder(ids = {89, 96, 35, 79, 37, 9})
public class NPCActionHandler implements Incoming {

    @Override
    public void handle(Player player, InBuffer in, int opcode) {
        if(player.isLocked())
            return;
        player.resetActions(true, true, true);

        int option = OPTIONS[opcode];
        if(option == 1) {
            int ctrlRun = in.readByteS();
            int targetIndex = in.readLEShort();
            handleAction(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 2) {
            int ctrlRun = in.readByteS();
            int targetIndex = in.readShortA();
            handleAction(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 3) {
            int ctrlRun = in.readByte();
            int targetIndex = in.readLEShort();
            handleAction(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 4) {
            int ctrlRun = in.readByte();
            int targetIndex = in.readLEShort();
            handleAction(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 5) {
            int ctrlRun = in.readByteS();
            int targetIndex = in.readLEShort();
            handleAction(player, option, targetIndex, ctrlRun);
            return;
        }
        if(option == 6) {
            int id = in.readShortA();
            NPCDef def = NPCDef.get(id);
            if(def == null)
                return;
            player.sendMessage(def.name);
            if(player.debug)
                debug(player, null, def, -1);
            return;
        }
        player.sendFilteredMessage("Unhandled npc action: option=" + option + " opcode=" + opcode);
    }

    private static void handleAction(Player player, int option, int npcIndex, int ctrlRun) {
        NPC npc = World.getNpc(npcIndex);
        if(npc == null)
            return;
        NPCDef def = npc.getDef();
        if(player.debug)
            debug(player, npc, def, option);
        player.face(npc);
        player.getMovement().setCtrlRun(ctrlRun == 1);
        if(option == def.attackOption) {
            player.getCombat().setTarget(npc);
            return;
        }
        if(npc.skipMovementCheck) {
            player.face(npc);
            int i = option - 1;
            if(i < 0 || i >= 5)
                return;
            NPCAction action = null;
            NPCAction[] actions = npc.actions;
            if(actions != null)
                action = actions[i];
            if(action == null && (actions = def.defaultActions) != null)
                action = actions[i];
            if(action != null) {
                action.handle(player, npc);
                return;
            }
            return;
        }
        TargetRoute.set(player, npc, () -> {
            int i = option - 1;
            if(i < 0 || i >= 5)
                return;
            NPCAction action = null;
            NPCAction[] actions = npc.actions;
            if(actions != null)
                action = actions[i];
            if(def.cryptic != null && def.cryptic.advance(player))
                return;
            if(def.anagram != null && def.anagram.advance(player))
                return;
            if(action == null && (actions = def.defaultActions) != null)
                action = actions[i];
            if(action != null) {
                action.handle(player, npc);
                player.face(npc);
                return;
            }
            /* default to a dialogue */
            Dialogue[][] dialogues = new Dialogue[][] {
                    new Dialogue[] {
                            new PlayerDialogue("Hello, how's it going?"),
                            new NPCDialogue(npc, "Not too bad, but I'm a little worried about the increase of goblins these days.").onDialogueOpened(() -> npc.faceTemp(player)),
                            new PlayerDialogue("Don't worry, I'll kill them."),
                    },
                    new Dialogue[] {
                            new PlayerDialogue("Hello, how's it going?"),
                            new NPCDialogue(npc, "How can I help you?").onDialogueOpened(() -> npc.faceTemp(player)),
                            new OptionsDialogue(
                                    new Option("Do you want to trade?", () -> {
                                        player.dialogue(
                                            new PlayerDialogue("Do you want to trade?"),
                                            new NPCDialogue(npc, "No, I have nothing I wish to get rid of. If you want to do some trading, there are plenty of shops and market stalls around though.")
                                        );
                                    }),
                                    new Option("I'm in search of a quest.", () -> {
                                        player.dialogue(
                                            new PlayerDialogue("I'm in search of a quest."),
                                            new NPCDialogue(npc, "I'm sorry I can't help you there.")
                                        );
                                    }),
                                    new Option("I'm in search of enemies to kill.", () -> {
                                        player.dialogue(
                                            new PlayerDialogue("I'm in search of enemies to kill."),
                                            new NPCDialogue(npc, "I've heard there are many fearsome creatures that dwell under the ground...")
                                        );
                                    }))
                    },
                    new Dialogue[] {
                            new PlayerDialogue("Hello, how's it going?"),
                            new NPCDialogue(npc, "None of your business.").onDialogueOpened(() -> npc.faceTemp(player)),
                    },
            };
            Dialogue[] randomDialogueSequence = dialogues[new Random().nextInt(dialogues.length)];
            player.dialogue(
                    randomDialogueSequence
            );
        });
    }

    private static void debug(Player player, NPC npc, NPCDef def, int option) {
        HashSet<Integer> showIds = new HashSet<>();
        if(def.showIds != null) {
            for(int id : def.showIds)
                showIds.add(id);
            showIds.remove(-1);
        }
        DebugMessage debug = new DebugMessage();
        if(option != -1)
            debug.add("option", option);
        debug.add("id", def.id + (showIds.isEmpty() ? "" : (" " + showIds.toString())));
        debug.add("name", def.name);
        if(npc != null) {
            debug.add("index", npc.getIndex());
            debug.add("x", npc.getAbsX());
            debug.add("y", npc.getAbsY());
            debug.add("z", npc.getHeight());
        }
        debug.add("options", Arrays.toString(def.options));
        debug.add("varpbitId", def.varpbitId);
        debug.add("varpId", def.varpId);
        if (def.varpbitId != -1 || def.varpId != -1)
            debug.add("variants", Arrays.toString(def.showIds));
        player.sendFilteredMessage("[NpcAction] " + debug.toString());
    }

}