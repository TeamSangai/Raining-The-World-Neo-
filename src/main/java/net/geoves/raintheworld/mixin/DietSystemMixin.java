package net.geoves.raintheworld.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.geoves.raintheworld.RainTheWorld;
import net.geoves.raintheworld.payloads.records.DietRecord;
import net.geoves.raintheworld.util.ModDataAttachments;
import net.geoves.raintheworld.util.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.stream.Stream;

@Mixin(Item.class)
public class DietSystemMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void dietUse(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        Class<DietRecord> data = DietRecord.class;
        if (player.getData(ModDataAttachments.DIET).equals("carnivore")){
            RainTheWorld.LOGGER.info("check 1");
            if (itemstack.is(ModTags.Items.PLAYER_HERBIVORE_FOOD)) {
                RainTheWorld.LOGGER.info("check 2");
                cir.setReturnValue(InteractionResultHolder.fail(itemstack));
                cir.cancel();
            }
        }

    }
}
