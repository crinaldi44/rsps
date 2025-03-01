package io.ruin.model.skills.cooking;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerCounter;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.dialogue.skill.SkillDialogue;
import io.ruin.model.inter.dialogue.skill.SkillItem;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemItemAction;
import io.ruin.model.stat.StatType;

public class JugOfWine {

    private static final int GRAPES = 1987;
    private static final int JUG_OF_WINE = 1993;
    private static final int JUG_OF_WATER = 1937;

    private static void startWine(Player player, Item primary, Item secondary) {
        SkillDialogue.make(player, new SkillItem(JUG_OF_WINE).name("Jug of wine")
                .addAction((p, amount, event) -> makeWine(player, primary, secondary, amount)));
    }

    private static void makeWine(Player player, Item primary, Item secondary, Integer amount) {
        player.startEvent(event -> {
            if (player.getStats().get(StatType.Cooking).fixedLevel < 35) {
                player.sendMessage("You do not have the required level to make wine.");
                return;
            }
            if (
                    player.getInventory().findItem(primary.getId()) != null && player.getInventory().findItem(secondary.getId()) != null
            ) {
                final int maxAmount = Math.min(amount, Math.min(primary.count(), secondary.count()));
                int made = 0;
                while(made++ < maxAmount) {
                    player.getInventory().remove(primary.getId(), 1);
                    player.getInventory().remove(secondary.getId(), 1);
                    player.getInventory().add(JUG_OF_WINE, 1);
                    player.animate(7529);
                    player.graphics(47);
                    player.privateSound(2489);
                    player.getStats().addXp(StatType.Cooking, 200.0, true);
                    PlayerCounter.JUGS_OF_WINE_MADE.increment(player, 1);
                    event.delay(2);
                }
                if (maxAmount > 0) {
                    player.sendMessage("You squeeze the grapes into the jug. The wine begins to ferment.");
                }
            }
        });
    }

    static {
        ItemItemAction.register(JUG_OF_WATER, GRAPES, JugOfWine::startWine);
    }
}
