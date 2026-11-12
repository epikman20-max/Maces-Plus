package org.BobTheWizard.maces_plus;

import java.util.function.Function;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MaceItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

/**
 * Holds every item this mod registers.
 *
 * <p>Note on mappings: this project uses Yarn, so the names here differ from the
 * official Fabric docs, which are written in Mojang mappings. The main ones:
 * {@code RegistryKey} (not {@code ResourceKey}), {@code Item.Settings} (not
 * {@code Item.Properties}), {@code Registries.ITEM} (not
 * {@code BuiltInRegistries.ITEM}) and {@code Identifier.of} (not
 * {@code Identifier.fromNamespaceAndPath}).
 */
public class ModItems {

    /**
     * Registers an item under this mod's namespace.
     *
     * @param name    the path of the item's identifier, e.g. {@code "diamond_mace"}
     * @param factory how to build the item from its settings, usually {@code Item::new}
     * @param settings the item's settings; its registry key is filled in here
     * @return the registered item, so it can be stored in a static field
     */
    public static <T extends Item> T register(String name, Function<Item.Settings, T> factory, Item.Settings settings) {
        // Build the key that identifies this item in the item registry.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Maces_plus.MOD_ID, name));

        // Since 1.21.2 an item has to know its own registry key, so attach it
        // to the settings before the item is constructed.
        T item = factory.apply(settings.registryKey(itemKey));

        return Registry.register(Registries.ITEM, itemKey, item);
    }

    /**
     * Builds the settings shared by every mace in this mod.
     *
     * <p>The tool component and the smash-attack behaviour come straight from
     * vanilla's {@link MaceItem}; only durability, damage and the repair
     * material change between tiers.
     *
     * @param attackDamage  bonus damage over the player's base of 1
     * @param durability    how many hits the mace survives
     * @param enchantability higher values give better enchantments per level
     * @param repairItem    the item that repairs this mace in an anvil
     * @param rarity        controls the colour of the item's name
     */
    private static Item.Settings maceSettings(float attackDamage, int durability, int enchantability, Item repairItem,
            Rarity rarity) {
        return new Item.Settings()
                .maxDamage(durability)
                .rarity(rarity)
                .enchantable(enchantability)
                .repairable(repairItem)
                // Mining speeds and the "can't be used on blocks" behaviour.
                .component(DataComponentTypes.TOOL, MaceItem.createToolComponent())
                // A mace costs 1 durability per hit and does not disable shields.
                .component(DataComponentTypes.WEAPON, new WeaponComponent(1))
                .attributeModifiers(maceAttributes(attackDamage));
    }

    /**
     * Builds the attack damage and attack speed modifiers for a mace. Vanilla's
     * mace is slow and heavy: these keep that feel across every tier.
     */
    private static AttributeModifiersComponent maceAttributes(float attackDamage) {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage,
                                EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, -3.4,
                                EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .build();
    }

    // The tier progression. Vanilla's mace sits at +5 damage / 500 durability.
    // Stone, gold and diamond hit softer than it. Netherite hits exactly as hard,
    // and the two are meant to be a real choice:
    //   - the netherite mace lasts four times as long and survives fire and lava;
    //   - only the vanilla mace can take Density, Breach and Wind Burst
    //     (see ModItemTagProvider, which leaves these out of that tag on purpose).
    public static final Item STONE_MACE = register("stone_mace", MaceItem::new,
            maceSettings(3.0F, 131, 5, Items.STONE, Rarity.COMMON));

    public static final Item GOLDEN_MACE = register("golden_mace", MaceItem::new,
            maceSettings(3.5F, 150, 22, Items.GOLD_INGOT, Rarity.UNCOMMON));

    public static final Item DIAMOND_MACE = register("diamond_mace", MaceItem::new,
            maceSettings(4.5F, 750, 10, Items.DIAMOND, Rarity.RARE));

    public static final Item NETHERITE_MACE = register("netherite_mace", MaceItem::new,
            maceSettings(5.0F, 2000, 15, Items.NETHERITE_INGOT, Rarity.EPIC).fireproof());

    /**
     * Exists so {@link Maces_plus} can force this class to load. The static
     * fields above are evaluated the first time the class is touched, and that
     * is what performs the registration.
     */
    public static void initialize() {
    }
}
