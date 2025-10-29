package cr0wbeak.cryingportals.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.world.dimension.NetherPortal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherPortal.class)
public class NetherPortalMixin {
    // Shadow IS_VALID_FRAME_BLOCK to recreate
    @Shadow @Final @Mutable
    private static AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK;

    /**
     * Modifies the final for a NetherPortal to recognize both Obsidian and Crying Obsidian
     * as a valid portal frame
     * @param ci The CallbackInfo for the mixin to operate
     */
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyValidFrameBlock(CallbackInfo ci) {
        IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
            // Allow obsidian OR crying obsidian
            return state.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.CRYING_OBSIDIAN);
        };
    }
}
