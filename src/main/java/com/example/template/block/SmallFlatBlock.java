package com.example.template.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class SmallFlatBlock extends FlatBlock {
    private final VoxelShape northShape, southShape, eastShape, westShape;

    public SmallFlatBlock(Settings settings, int corner) {
        super(settings);
        double s = 10.6667d;
        double g = 16.0d - s;
        double y1, y2;
        if (corner == 0 || corner == 1) { y1 = g; y2 = 16.0d; }
        else                             { y1 = s; y2 = 21.3333d; }

        // 模型Y旋转: y=90(x,z)->(16-z,x), y=180->(16-x,16-z), y=270->(z,16-x)
        if (corner == 0 || corner == 2) {
            // 1/3号: 原始 x=g..16, z=0..1
            this.northShape = Block.createCuboidShape(g,  y1, 0,  16.0, y2, 1);   // north
            this.southShape = Block.createCuboidShape(0,  y1, 15, s,    y2, 16);  // south
            this.eastShape  = Block.createCuboidShape(15, y1, g,  16.0, y2, 16);  // east
            this.westShape  = Block.createCuboidShape(0,  y1, 0,  1,    y2, s);   // west
        } else {
            // 2/4号: 原始 x=0..s, z=0..1
            this.northShape = Block.createCuboidShape(0,  y1, 0,  s,    y2, 1);   // north
            this.southShape = Block.createCuboidShape(g,  y1, 15, 16.0, y2, 16);  // south
            this.eastShape  = Block.createCuboidShape(15, y1, 0,  16.0, y2, s);   // east
            this.westShape  = Block.createCuboidShape(0,  y1, g,  1,    y2, 16);  // west
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        switch (state.get(FACING)) {
            case NORTH: return northShape;
            case SOUTH: return southShape;
            case EAST:  return eastShape;
            case WEST:  return westShape;
            default:    return northShape;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return getOutlineShape(state, world, pos, ctx);
    }
}