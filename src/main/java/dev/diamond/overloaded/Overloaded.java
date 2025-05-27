package dev.diamond.overloaded;

import dev.diamond.overloaded.mixin.EnchantmentMixin;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Overloaded implements ModInitializer {

    public static final String modid = "overloaded";
    public static final Logger LOGGER = LoggerFactory.getLogger("Overloaded");


    @Override
    public void onInitialize() {
        long start = System.currentTimeMillis();
        //

        //EnchantmentMixin.setMaxLevel(Integer.MAX_VALUE);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            // Register Commands Here
            OverloadCommand.register(dispatcher, registryAccess);
        });

        //
        long initTime = System.currentTimeMillis() - start;
        LOGGER.info("Mod " + modid + " initialized in " + initTime + " millisecond(s)!");
    }

    public static void forceAddEnchantment(ItemStack stack, RegistryEntry<Enchantment> e, int level) {
        stack.addEnchantment(e, level);
    }
}
