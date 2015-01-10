package com.javb.extrastuff.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.javb.extrastuff.ExtraStuff;
import com.javb.extrastuff.init.ModBlocks;
import com.javb.extrastuff.init.ModCreativeTabs;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.tileentity.TileEntityRubyUpgrader;
import com.javb.extrastuff.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRubyUpgrader extends BlockContainer
{
    private final Random random = new Random();
    public IIcon topBlockIcon;
    public IIcon sideBlockIcon;

    public BlockRubyUpgrader() {
        super(Material.rock);
        this.setCreativeTab(ModCreativeTabs.tabRubyStuff);
        this.setBlockName("rubyUpgrader");
        this.setHardness(2f);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(ModBlocks.rubyUpgrader);
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		topBlockIcon = iconRegister.registerIcon(Reference.MODID + ":" + "rubyUpgraderTop");
		sideBlockIcon = iconRegister.registerIcon(Reference.MODID + ":" + "rubyUpgraderSide");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side < 2) {
			return this.topBlockIcon;
		} else {
			return this.sideBlockIcon;
		}
	}
	
	public void dropItem(ItemStack itemstack, World world, int x, int y, int z) {
		float f = this.random.nextFloat() * 0.8F + 0.1F;
        float f1 = this.random.nextFloat() * 0.8F + 0.1F;
        EntityItem entityitem;

        for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
        {
            int j1 = this.random.nextInt(21) + 10;

            if (j1 > itemstack.stackSize)
            {
                j1 = itemstack.stackSize;
            }

            itemstack.stackSize -= j1;
            entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
            float f3 = 0.05F;
            entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
            entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
            entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);

            if (itemstack.hasTagCompound())
            {
                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
            }
        }
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntityRubyUpgrader tileEntiyUpgrader = (TileEntityRubyUpgrader) world.getTileEntity(x, y, z);

        if (tileEntiyUpgrader != null)
        {
            for (int i1 = 0; i1 < tileEntiyUpgrader.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = tileEntiyUpgrader.getStackInSlot(i1);

                if (itemstack != null)
                {
                	dropItem(itemstack, world, x, y, z);
                }
            }
            world.func_147453_f(x, y, z, block);
        }
	
        super.breakBlock(world, x, y, z, block, meta);
	}
	
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityRubyUpgrader tileentityfurnace = (TileEntityRubyUpgrader)world.getTileEntity(x, y, z);

            if (tileentityfurnace != null)
            {
            	
            }
            LogHelper.info("opening gui");
            player.openGui(ExtraStuff.instance, 0, world, x, y, z);
            return true;
        }
    }
    
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityRubyUpgrader();
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(ModBlocks.rubyUpgrader);
    }
}
