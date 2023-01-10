package net.diamonddev.overloaded;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.diamonddev.overloaded.Overloaded;
import net.minecraft.command.argument.EnchantmentArgumentType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Collection;

import static net.minecraft.server.command.CommandManager.*;

public class OverloadCommand {

    private static final String TARGET_ARG = "targets";
    private static final String ENCH_ARG = "enchantment";
    private static final String LVL_ARG = "level";



    private static final DynamicCommandExceptionType FAILED_ITEMLESS_EXCEPTION = new DynamicCommandExceptionType((entityName) -> {
        return Text.translatable("commands.enchant.failed.itemless", entityName);
    });


    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("overload").requires((source) -> source.hasPermissionLevel(2))

                .then(argument(TARGET_ARG, EntityArgumentType.entities())
                        .then(argument(ENCH_ARG, EnchantmentArgumentType.enchantment())
                                .executes(context -> execute(context, 1))

                                .then(CommandManager.argument(LVL_ARG, IntegerArgumentType.integer())
                                    .executes(context -> execute(context, IntegerArgumentType.getInteger(context, LVL_ARG)))
        ))));
    }


    private static int execute(CommandContext<ServerCommandSource> context, int lvl) throws CommandSyntaxException {
        Enchantment e = EnchantmentArgumentType.getEnchantment(context, ENCH_ARG);
        Collection<? extends Entity> targets = EntityArgumentType.getEntities(context, TARGET_ARG);

        for (Entity entity : targets)  {
            if (entity instanceof LivingEntity target) {
                ItemStack stack = target.getMainHandStack();
                if (!stack.isEmpty()) {
                    Overloaded.forceAddEnchantment(stack, e, lvl);
                } else throw FAILED_ITEMLESS_EXCEPTION.create(target.getName().getString());
            }
        }

        return 1;
    }

}
