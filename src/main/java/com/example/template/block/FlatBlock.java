package com.example.template.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class FlatBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    // 碰撞箱靠FACING侧（贴墙）
    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0, 0, 0,  16, 16, 1);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0, 0, 15, 16, 16, 16);
    private static final VoxelShape EAST_SHAPE  = Block.createCuboidShape(15, 0, 0, 16, 16, 16);
    private static final VoxelShape WEST_SHAPE  = Block.createCuboidShape(0, 0, 0,  1,  16, 16);

    public FlatBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        switch (state.get(FACING)) {
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case EAST:  return EAST_SHAPE;
            case WEST:  return WEST_SHAPE;
            default:    return NORTH_SHAPE;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return getOutlineShape(state, world, pos, ctx);
    }
}