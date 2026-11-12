package org.BobTheWizard.maces_plus;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Maces_plus implements ModInitializer {
    /**
     * The mod id, matching the "id" field in fabric.mod.json. Every Identifier
     * this mod creates is namespaced with it, so keep the two in sync.
     */
    public static final String MOD_ID = "maces_plus";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Calling a method on these classes triggers their static initializers,
        // which is what actually registers the content. Without these calls the
        // items would never be added to the registry.
        ModItems.initialize();
        ModItemGroups.initialize();

        LOGGER.info("Maces Plus loaded");
    }
}
