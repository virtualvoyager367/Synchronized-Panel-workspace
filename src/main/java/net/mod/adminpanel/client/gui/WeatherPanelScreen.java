package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.WeatherPanelMenu;
import net.mod.adminpanel.network.WeatherPanelButtonMessage;
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

public class WeatherPanelScreen extends AbstractContainerScreen<WeatherPanelMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = WeatherPanelMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_clear;
	Button button_rain;
	Button button_thunderstorm;

	public WeatherPanelScreen(WeatherPanelMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 137;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof WeatherPanelScreen sc) {

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/weather_panel.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.weather_panel.label_admin_panel_weather"), 31, 9, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_clear = Button.builder(Component.translatable("gui.admin_panel.weather_panel.button_clear"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new WeatherPanelButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				WeatherPanelButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 59, this.topPos + 29, 51, 20).build();
		guistate.put("button:button_clear", button_clear);
		this.addRenderableWidget(button_clear);
		button_rain = Button.builder(Component.translatable("gui.admin_panel.weather_panel.button_rain"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new WeatherPanelButtonMessage(1, x, y, z, getEditBoxAndCheckBoxValues()));
				WeatherPanelButtonMessage.handleButtonAction(entity, 1, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 62, this.topPos + 53, 46, 20).build();
		guistate.put("button:button_rain", button_rain);
		this.addRenderableWidget(button_rain);
		button_thunderstorm = Button.builder(Component.translatable("gui.admin_panel.weather_panel.button_thunderstorm"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new WeatherPanelButtonMessage(2, x, y, z, getEditBoxAndCheckBoxValues()));
				WeatherPanelButtonMessage.handleButtonAction(entity, 2, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 41, this.topPos + 77, 88, 20).build();
		guistate.put("button:button_thunderstorm", button_thunderstorm);
		this.addRenderableWidget(button_thunderstorm);
	}
}
