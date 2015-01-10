package com.javb.extrastuff.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.javb.extrastuff.inventory.ContainerRubyCrafting;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRubyCrafting extends GuiContainer
{
    private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation(Reference.MODID + ":textures/gui/rubyCraftingTable.png");
    private static final String __OBFID = "CL_00000750";

    public GuiRubyCrafting(InventoryPlayer p_i1084_1_, World p_i1084_2_, int p_i1084_3_, int p_i1084_4_, int p_i1084_5_)
    {
        super(new ContainerRubyCrafting(p_i1084_1_, p_i1084_2_, p_i1084_3_, p_i1084_4_, p_i1084_5_));
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
    	// added 32 to the x.
        this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 50, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		LogHelper.info("[Container Ruby Crafting] mouseClicked");
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}
    
}