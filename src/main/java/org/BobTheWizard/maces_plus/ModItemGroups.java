package org.BobTheWizard.maces_plus;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Creates the mod's own creative inventory tab and also adds the maces to
 * vanilla's Combat tab, so they show up where players expect to find weapons.
 */
public class ModItemGroups {

    public static final RegistryKey<ItemGroup> MACES_PLUS_KEY =
            RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Maces_plus.MOD_ID, "maces_plus"));

    public static final ItemGroup MACES_PLUS_GROUP = FabricItemGroup.builder()
            // Resolved by the translation key in en_us.json.
            .displayName(Text.translatable("itemGroup.maces_plus.maces_plus"))
            .icon(() -> new ItemStack(ModItems.NETHERITE_MACE))
            .entries((displayContext, entries) -> {
                entries.add(ModItems.STONE_MACE);
                entries.add(ModItems.GOLDEN_MACE);
                entries.add(ModItems.DIAMOND_MACE);
                entries.add(ModItems.NETHERITE_MACE);
            })
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, MACES_PLUS_KEY, MACES_PLUS_GROUP);

        // Also list them alongside vanilla's weapons.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(ModItems.STONE_MACE);
            entries.add(ModItems.GOLDEN_MACE);
            entries.add(ModItems.DIAMOND_MACE);
            entries.add(ModItems.NETHERITE_MACE);
        });
    }
}
