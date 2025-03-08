package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.GamemodeRandomChangerMenu;
import net.mod.adminpanel.network.GamemodeRandomChangerButtonMessage;
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

public class GamemodeRandomChangerScreen extends AbstractContainerScreen<GamemodeRandomChangerMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = GamemodeRandomChangerMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_survival;
	Button button_creative;
	Button button_adventure;
	Button button_spectator;

	public GamemodeRandomChangerScreen(GamemodeRandomChangerMenu container, Inventory inventory, Component text) {
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
		if (Minecraft.getInstance().screen instanceof GamemodeRandomChangerScreen sc) {

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/gamemode_random_changer.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.gamemode_random_changer.label_admin_panel_gamemode_changer"), 9, 7, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_survival = Button.builder(Component.translatable("gui.admin_panel.gamemode_random_changer.button_survival"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new GamemodeRandomChangerButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				GamemodeRandomChangerButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 56, this.topPos + 26, 67, 20).build();
		guistate.put("button:button_survival", button_survival);
		this.addRenderableWidget(button_survival);
		button_creative = Button.builder(Component.translatable("gui.admin_panel.gamemode_random_changer.button_creative"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new GamemodeRandomChangerButtonMessage(1, x, y, z, getEditBoxAndCheckBoxValues()));
				GamemodeRandomChangerButtonMessage.handleButtonAction(entity, 1, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 56, this.topPos + 55, 67, 20).build();
		guistate.put("button:button_creative", button_creative);
		this.addRenderableWidget(button_creative);
		button_adventure = Button.builder(Component.translatable("gui.admin_panel.gamemode_random_changer.button_adventure"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new GamemodeRandomChangerButtonMessage(2, x, y, z, getEditBoxAndCheckBoxValues()));
				GamemodeRandomChangerButtonMessage.handleButtonAction(entity, 2, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 54, this.topPos + 83, 72, 20).build();
		guistate.put("button:button_adventure", button_adventure);
		this.addRenderableWidget(button_adventure);
		button_spectator = Button.builder(Component.translatable("gui.admin_panel.gamemode_random_changer.button_spectator"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new GamemodeRandomChangerButtonMessage(3, x, y, z, getEditBoxAndCheckBoxValues()));
				GamemodeRandomChangerButtonMessage.handleButtonAction(entity, 3, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 54, this.topPos + 113, 72, 20).build();
		guistate.put("button:button_spectator", button_spectator);
		this.addRenderableWidget(button_spectator);
	}
}
