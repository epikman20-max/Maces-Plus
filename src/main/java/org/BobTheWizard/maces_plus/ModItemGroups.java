package org.BobTheWizard.maces_plus;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

/**
 * Adds the maces to vanilla's Combat tab in the creative inventory, right
 * after the vanilla mace.
 */
public class ModItemGroups {

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries ->
                entries.addAfter(Items.MACE,
                        ModItems.STONE_MACE,
                        ModItems.GOLDEN_MACE,
                        ModItems.DIAMOND_MACE,
                        ModItems.NETHERITE_MACE));
    }
}
