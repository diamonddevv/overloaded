package net.diamonddev.overloaded;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OverloadedServer implements DedicatedServerModInitializer {

	public static final String modid = "overloaded";
	public static final Logger LOGGER = LoggerFactory.getLogger("Overloaded");

	@Override
	public void onInitializeServer() {
		long start = System.currentTimeMillis();
		//

		//
		long initTime = System.currentTimeMillis() - start;
		LOGGER.info("Mod " + modid + " initialized in " + initTime + " millisecond(s)!");
	}

	public static void putEnchantment(ItemStack stack, EnchantmentLevelEntry ele) {
		Map<Enchantment, Integer> map = EnchantmentHelper.get(stack);
		map.put(ele.enchantment, ele.level);
		EnchantmentHelper.set(map, stack);
	}
}
