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
import org.apache.commons.logging.LogFactory;
import org.jline.utils.Log;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
        if (level.isClientSide) {
            RainTheWorld.LOGGER.info(player.getData(ModDataAttachments.DIET));
        }

    }
}
