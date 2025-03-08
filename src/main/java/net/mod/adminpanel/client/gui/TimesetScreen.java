package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.TimesetMenu;
import net.mod.adminpanel.network.TimesetButtonMessage;
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

public class TimesetScreen extends AbstractContainerScreen<TimesetMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = TimesetMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_midnight;
	Button button_day;
	Button button_night;
	Button button_noon;

	public TimesetScreen(TimesetMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 96;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof TimesetScreen sc) {

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/timeset.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.timeset.label_admin_panel_time"), 40, 9, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_midnight = Button.builder(Component.translatable("gui.admin_panel.timeset.button_midnight"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TimesetButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				TimesetButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 83, this.topPos + 58, 67, 20).build();
		guistate.put("button:button_midnight", button_midnight);
		this.addRenderableWidget(button_midnight);
		button_day = Button.builder(Component.translatable("gui.admin_panel.timeset.button_day"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TimesetButtonMessage(1, x, y, z, getEditBoxAndCheckBoxValues()));
				TimesetButtonMessage.handleButtonAction(entity, 1, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 29, this.topPos + 32, 40, 20).build();
		guistate.put("button:button_day", button_day);
		this.addRenderableWidget(button_day);
		button_night = Button.builder(Component.translatable("gui.admin_panel.timeset.button_night"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TimesetButtonMessage(2, x, y, z, getEditBoxAndCheckBoxValues()));
				TimesetButtonMessage.handleButtonAction(entity, 2, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 23, this.topPos + 58, 51, 20).build();
		guistate.put("button:button_night", button_night);
		this.addRenderableWidget(button_night);
		button_noon = Button.builder(Component.translatable("gui.admin_panel.timeset.button_noon"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TimesetButtonMessage(3, x, y, z, getEditBoxAndCheckBoxValues()));
				TimesetButtonMessage.handleButtonAction(entity, 3, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 93, this.topPos + 33, 46, 20).build();
		guistate.put("button:button_noon", button_noon);
		this.addRenderableWidget(button_noon);
	}
}
