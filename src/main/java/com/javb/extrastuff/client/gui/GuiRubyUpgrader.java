package com.javb.extrastuff.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.javb.extrastuff.inventory.ContainerRubyUpgrader;
import com.javb.extrastuff.reference.Reference;
import com.javb.extrastuff.tileentity.TileEntityRubyUpgrader;
import com.javb.extrastuff.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRubyUpgrader extends GuiContainer {
	private static final ResourceLocation upgraderTextures = new ResourceLocation(Reference.MODID + ":textures/gui/rubyUpgrader.png");
	private TileEntityRubyUpgrader tileUpgrader;
	private static final String __OBFID = "CL_00000758";

	public GuiRubyUpgrader(InventoryPlayer inventoryPlayer, TileEntityRubyUpgrader tileEntity) {
		// the container is instantiated and passed to the superclass for
		// handling
		super(new ContainerRubyUpgrader(inventoryPlayer, tileEntity));
		this.tileUpgrader = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		//String s = this.tileUpgrader.hasCustomInventoryName() ? this.tileUpgrader.getInventoryName() : I18n.format(this.tileUpgrader.getInventoryName(), new Object[0]);
		String s = "Ruby Upgrader";
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path
		mc.renderEngine.bindTexture(upgraderTextures);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		if (this.tileUpgrader.isBurning() || true) {
			//LogHelper.info("gui upgrader is burning.");
            int scaled = this.tileUpgrader.getBurnTimeRemainingScaled(15);
           // LogHelper.info("burnTimeRemaingScaled: " + i1);
            this.drawTexturedModalRect(x + 53, y + 35 + 14 - scaled, 176, 46 - scaled, 15, scaled + 1);
            //this.drawTexturedModalRect(x + 56, y + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
            scaled = this.tileUpgrader.getUpgradeProgressScaled(24);
            this.drawTexturedModalRect(x + 102, y + 35, 176, 14, scaled + 1, 16);
            //this.drawTexturedModalRect(x + 79, y + 34, 176, 14, scaled + 1, 16);
        }
	}

}