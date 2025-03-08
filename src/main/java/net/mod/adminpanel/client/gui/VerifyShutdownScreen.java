package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.VerifyShutdownMenu;
import net.mod.adminpanel.network.VerifyShutdownButtonMessage;
import net.mod.adminpanel.init.AdminPanelModScreens.WidgetScreen;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class VerifyShutdownScreen extends AbstractContainerScreen<VerifyShutdownMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = VerifyShutdownMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_yes;
	Button button_no;

	public VerifyShutdownScreen(VerifyShutdownMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 147;
		this.imageHeight = 68;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof VerifyShutdownScreen sc) {

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/verify_shutdown.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.verify_shutdown.label_are_you_sure_you_want"), 7, 10, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.verify_shutdown.label_to_shutdown_the_server"), 17, 18, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_yes = Button.builder(Component.translatable("gui.admin_panel.verify_shutdown.button_yes"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new VerifyShutdownButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				VerifyShutdownButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 91, this.topPos + 34, 40, 20).build();
		guistate.put("button:button_yes", button_yes);
		this.addRenderableWidget(button_yes);
		button_no = Button.builder(Component.translatable("gui.admin_panel.verify_shutdown.button_no"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new VerifyShutdownButtonMessage(1, x, y, z, getEditBoxAndCheckBoxValues()));
				VerifyShutdownButtonMessage.handleButtonAction(entity, 1, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 17, this.topPos + 34, 35, 20).build();
		guistate.put("button:button_no", button_no);
		this.addRenderableWidget(button_no);
	}
}
