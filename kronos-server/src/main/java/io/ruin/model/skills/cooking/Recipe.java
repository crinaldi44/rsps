package io.ruin.model.skills.cooking;

import io.ruin.cache.ItemID;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.actions.ItemItemAction;
import io.ruin.model.stat.StatRequirement;
import io.ruin.model.stat.StatType;

/**
 * Represents combinable items that follow the pattern of "use x on x".
 * @author chrisrinaldi
 */
public enum Recipe {

    BOWL_ADD_EGG(1, 1944, 1923, 7076, 5, "uncooked egg"),
    SCRAMBLED_EGG_ADD_TOMATO(23, 1982, 7076, 7064, 0, "egg and tomato"),
    BAKED_POTATO_ADD_BUTTER(39, 6697, 6701, 6703, 40, "potato with butter"),
    EGG_POTATO(51, 7064, 6703, 7056, 45, "egg potato"),
    MUSHROOM_POTATO(64, 7066, 6703, 7058, 55, "mushroom potato"),
    BOWL_OF_WATER_ADD_POTATO(25, 1942, 1921, 1997, 5, "incomplete stew"),
    BOWL_OF_WATER_ADD_MEAT(25, 2142, 1921, 1997, 5, "incomplete stew"),
    INCOMPLETE_STEW_ADD_MEAT(25,2142,1997,2001,5, "stew"),
    INCOMPLETE_STEW_ADD_POTATO(25, 1942, 1999, 2001, 5, "stew"),
    FULL_CAKE_ADD_CHOCOLATE_BAR(50, 1973, 1891, 1897, 5, "chocolate cake"),
    FULL_CAKE_ADD_CHOCOLATE_DUST(50, 1973, 1975, 1897, 5, "chocolate cake");

    private static final int EGG_ITEM_ID = 1944;
    private static final int MILK_ITEM_ID = 1927;
    private static final int FLOUR_ITEM_ID = 1933;
    private static final int CAKE_TIN_ID = 1887;

    public final int levelRequirement;
    public final int firstItemId;
    public final int secondItemId;
    public final int combineItemId;
    public final int xpReward;
    public final String combineItemName;

    /**
     * Creates a new Recipe.
     * @param levelRequirement
     * @param firstItem
     * @param secondItem
     * @param xpReward
     */
    Recipe(int levelRequirement, int firstItem, int secondItem, int combineItem, int xpReward, String combineItemName) {
        this.levelRequirement = levelRequirement;
        this.firstItemId = firstItem;
        this.secondItemId = secondItem;
        this.combineItemId = combineItem;
        this.xpReward = xpReward;
        this.combineItemName = combineItemName;
    }

    private static void makeCake(Player p, Item first, Item second) {
        Item flour = p.getInventory().findItem(FLOUR_ITEM_ID);
        Item egg = p.getInventory().findItem(EGG_ITEM_ID);
        Item milk = p.getInventory().findItem(MILK_ITEM_ID);
        if (flour != null && egg != null && milk != null) {
            if (p.getStats().check(new StatRequirement(StatType.Cooking, 40))) {
                if (!p.getInventory().contains(CAKE_TIN_ID)) {
                    p.sendFilteredMessage("You don't have a cake tin.");
                } else {
                    p.sendFilteredMessage("You combine the ingredients into a tin to make a cake.");
                    Item cakeTin = p.getInventory().findItem(CAKE_TIN_ID);
                    cakeTin.remove(1);
                    flour.remove(1);
                    egg.remove(1);
                    milk.remove(1);
                    p.getInventory().add(1889);
                }
            } else {
                p.dialogue(new MessageDialogue("You need a Cooking level of at least 40 to make cake."));
            }
        }
    }

    private static void doughPrompt() {

    }

    static {
        // Register combinables.
        for (Recipe recipe : Recipe.values()) {
            ItemItemAction.register(recipe.firstItemId, recipe.secondItemId, (p, primary, secondary) -> {
                if (!p.getStats().check(new StatRequirement(StatType.Cooking, recipe.levelRequirement))) {
                    p.dialogue(new MessageDialogue("You need a Cooking level of " + recipe.levelRequirement + " to make " + recipe.name()));
                }
                primary.remove();
                secondary.remove();
                p.getInventory().add(recipe.combineItemId);
                p.sendFilteredMessage("You add the " + primary.getDef().name.toLowerCase() + " to the "
                        + secondary.getDef().name.toLowerCase() + ".");
                p.getStats().addXp(StatType.Cooking, recipe.xpReward, true);
            });
        }

        ItemItemAction.register(1944, 1933, Recipe::makeCake);
        ItemItemAction.register(1944, 1927, Recipe::makeCake);
        ItemItemAction.register(1933, 1927, Recipe::makeCake);

        // TODO: Attach ItemItemAction for pot of flour and jug of water.
//        ItemItemAction.register();
    }

}
