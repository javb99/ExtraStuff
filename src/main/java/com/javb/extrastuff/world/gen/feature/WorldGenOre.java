package com.javb.extrastuff.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator{

	public Block ore;
	public int maxVein;
	public int perChunk;
	public int minY;
	public int maxY;
	
	/**
	 * 
	 * @param ore: block to generate
	 * @param maxVein: maximum blocks per vein
	 * @param perChunk: number of tries to spawn a vein
	 * @param minY: minimum height the ore will spawn
	 * @param maxY maximum height the ore will spawn
	 */
	public WorldGenOre(Block ore, int maxVein, int perChunk, int minY, int maxY) {
		this.ore = ore;
		this.maxVein = maxVein;
		this.perChunk = perChunk;
		this.minY = minY;
		this.maxY = maxY;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateOverWorld(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}

	private void generateEnd(World world, Random random, int x, int z) {
	}

	private void generateOverWorld(World world, Random random, int x, int z) {
		generateOre(this.ore, world, random, x, z, 16, 16, this.maxVein, this.perChunk, minY, maxY, Blocks.stone);
	}

	private void generateNether(World world, Random random, int x, int z) {
	}
	
	public void generateOre(Block block, World world, Random random, int chunk_x, int chunk_z, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY, Block generateIn) {
		int heightRange = maxY - minY;
		WorldGenMinable worldGenMinable = new WorldGenMinable(block, maxVeinSize, generateIn);
		for (int k1 = 0; k1 < chancesToSpawn; ++k1) {
			int xrand = random.nextInt(16);
			int yrand = random.nextInt(heightRange) + minY;
			int zrand = random.nextInt(16);
			worldGenMinable.generate(world, random, chunk_x+xrand, yrand, chunk_z+zrand);
		}
	}
}
