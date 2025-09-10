package net.geoves.raintheworld.item;

import net.geoves.raintheworld.gui.screen.DietTypeSelectScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DietSelector extends Item {
    public DietSelector(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
        itemstack.consume(1, player);
        if (level.isClientSide){
            Minecraft.getInstance().setScreen(new DietTypeSelectScreen(Component.empty()));
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
