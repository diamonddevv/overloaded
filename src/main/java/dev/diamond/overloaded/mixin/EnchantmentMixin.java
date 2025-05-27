package dev.diamond.overloaded.mixin;

import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Enchantment.class)
public interface EnchantmentMixin {
    @Accessor("MAX_LEVEL") static int getMaxLevel() { throw new RuntimeException("oopsie"); }
    @Accessor("MAX_LEVEL") static void setMaxLevel(int i) { throw new RuntimeException("oopsie"); }
}
