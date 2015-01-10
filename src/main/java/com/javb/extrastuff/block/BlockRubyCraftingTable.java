package com.javb.extrastuff.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.javb.extrastuff.ExtraStuff;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.utility.MultiBlockHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRubyCraftingTable extends BlockES{
	
	public IIcon topBlockIcon;
    public IIcon sideBlockIcon;
	
	public BlockRubyCraftingTable(String name) {
		super(Material.rock, name);
		this.setHardness(2.0f);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		topBlockIcon = iconRegister.registerIcon(Reference.MODID + ":" + "rubyCraftingTable_top");
		sideBlockIcon = iconRegister.registerIcon(Reference.MODID + ":" + "rubyCraftingTable_side");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side < 2) {
			return this.topBlockIcon;
		} else {
			return this.sideBlockIcon;
		}
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
        	player.openGui(ExtraStuff.instance, 1, world, x, y, z);
        	/*if (MultiBlockHelper.isRubyPyramid(world, x, y, z)) {
        		player.openGui(ExtraStuff.instance, 1, world, x, y, z);
        	} else {
        		player.addChatMessage(new ChatComponentTranslation("[Extra Stuff] Not valid multi-block structure!"));
        	}*/
            return true;
        }
    }
}
