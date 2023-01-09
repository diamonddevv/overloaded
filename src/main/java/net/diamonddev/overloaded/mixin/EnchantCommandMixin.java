package net.diamonddev.overloaded.mixin;

import net.diamonddev.overloaded.OverloadedServer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.EnchantCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantCommand.class)
public class EnchantCommandMixin {

    @Redirect(
            method = "execute",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"
            )
    )
    private static int overloaded$ignoreLimit(Enchantment instance) {
        return Integer.MAX_VALUE;
    }

    @Redirect(
            method = "execute",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;addEnchantment(Lnet/minecraft/enchantment/Enchantment;I)V"
            )
    )
    private static void overloaded$redirectEnchanting(ItemStack instance, Enchantment enchantment, int level) {
        OverloadedServer.putEnchantment(instance, new EnchantmentLevelEntry(enchantment, level));
    }
}
