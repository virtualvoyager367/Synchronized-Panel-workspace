package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.HLAMenuMenu;
import net.mod.adminpanel.network.HLAMenuButtonMessage;
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

public class HLAMenuScreen extends AbstractContainerScreen<HLAMenuMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = HLAMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_hla_server_settings;

	public HLAMenuScreen(HLAMenuMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof HLAMenuScreen sc) {

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/hla_menu.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.hla_menu.label_admin_panel_other_hla"), 22, 6, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_hla_server_settings = Button.builder(Component.translatable("gui.admin_panel.hla_menu.button_hla_server_settings"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new HLAMenuButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				HLAMenuButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 26, this.topPos + 26, 124, 20).build();
		guistate.put("button:button_hla_server_settings", button_hla_server_settings);
		this.addRenderableWidget(button_hla_server_settings);
	}
}
