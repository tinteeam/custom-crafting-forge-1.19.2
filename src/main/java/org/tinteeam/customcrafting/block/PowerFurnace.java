package org.tinteeam.customcrafting.block;
import static org.tinteeam.customcrafting.block.ModBlocks.registerBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.RegistryObject;

import org.tinteeam.customcrafting.block.entity.ModBlockEntities;
import org.tinteeam.customcrafting.block.entity.PowerFurnaceBlockEntity;
import org.tinteeam.customcrafting.item.ModCreativeModeTab;

import javax.annotation.Nullable;

public class PowerFurnace extends BaseEntityBlock {
    public  static final RegistryObject<Block> POWER_FURNACE = registerBlock("power_furnace",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.customcrafting);


    @Override
    public RenderShape getRenderShape(BlockState  p_49232_) {
        return RenderShape.MODEL;
    }
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof PowerFurnaceBlockEntity) {
                ((PowerFurnaceBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof PowerFurnaceBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (PowerFurnaceBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }



    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PowerFurnaceBlockEntity(pos, state);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(level, ModBlockEntities.POWER_FURNACE.get(), PowerFurnaceBlockEntity::tick);
    }



}
