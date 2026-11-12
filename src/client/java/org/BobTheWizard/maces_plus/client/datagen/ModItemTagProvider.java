package org.BobTheWizard.maces_plus.client.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import org.BobTheWizard.maces_plus.ModItems;

/**
 * Decides which enchantments the maces can take. Enchantments look at item
 * tags, not at the item class, so without these the maces could not be
 * enchanted at all.
 *
 * <p>They get everything the vanilla mace gets <em>except</em>
 * {@link ItemTags#MACE_ENCHANTABLE} (Density, Breach and Wind Burst). Keeping
 * those exclusive to the vanilla mace is what gives it a reason to exist next
 * to the equally strong, much tougher netherite mace.
 */
public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, registries);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {
        addMaces(ItemTags.DURABILITY_ENCHANTABLE);  // Unbreaking, Mending
        addMaces(ItemTags.WEAPON_ENCHANTABLE);      // Smite, Bane of Arthropods
        addMaces(ItemTags.FIRE_ASPECT_ENCHANTABLE); // Fire Aspect
    }

    private void addMaces(TagKey<Item> tag) {
        valueLookupBuilder(tag)
                .add(ModItems.STONE_MACE)
                .add(ModItems.GOLDEN_MACE)
                .add(ModItems.DIAMOND_MACE)
                .add(ModItems.NETHERITE_MACE);
    }
}
