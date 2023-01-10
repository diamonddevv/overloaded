package net.diamonddev.overloaded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
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

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            // Register Commands Here
            OverloadCommand.register(dispatcher);
        });

        //
        long initTime = System.currentTimeMillis() - start;
        LOGGER.info("Mod " + modid + " initialized in " + initTime + " millisecond(s)!");
    }

    public static void forceAddEnchantment(ItemStack stack, Enchantment e, int level) {
        Map<Enchantment, Integer> map = EnchantmentHelper.get(stack);
        map.put(e, level);
        EnchantmentHelper.set(map, stack);
    }
}
