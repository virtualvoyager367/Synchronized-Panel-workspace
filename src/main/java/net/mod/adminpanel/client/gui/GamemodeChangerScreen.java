package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.GamemodeChangerMenu;
import net.mod.adminpanel.procedures.HLACheckerProcedure;
import net.mod.adminpanel.network.GamemodeChangerButtonMessage;
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

public class GamemodeChangerScreen extends AbstractContainerScreen<GamemodeChangerMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = GamemodeChangerMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_you;
	Button button_everyone;
	Button button_specific_player;
	Button button_random_player;

	public GamemodeChangerScreen(GamemodeChangerMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 171;
		this.imageHeight = 201;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof GamemodeChangerScreen sc) {

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/gamemode_changer.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.gamemode_changer.label_admin_panel_gamemode_changer"), 8, 10, -12829636, false);
		if (HLACheckerProcedure.execute(entity))
			guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.gamemode_changer.label_all_options_below_are_for_hla"), 65, 126, -12829636, false);
		if (HLACheckerProcedure.execute(entity))
			guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.gamemode_changer.label_empty"), 1, 115, -12829636, false);
		if (HLACheckerProcedure.execute(entity))
			guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.gamemode_changer.label_empty1"), 3, 134, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_you = Button.builder(Component.translatable("gui.admin_panel.gamemode_changer.button_you"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new GamemodeChangerButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				GamemodeChangerButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 65, this.topPos + 30, 40, 20).build();
		guistate.put("button:button_you", button_you);
		this.addRenderableWidget(button_you);
		button_everyone = Button.builder(Component.translatable("gui.admin_panel.gamemode_changer.button_everyone"), e -> {
			if (HLACheckerProcedure.execute(entity)) {
				PacketDistributor.sendToServer(new GamemodeChangerButtonMessage(1, x, y, z, getEditBoxAndCheckBoxValues()));
				GamemodeChangerButtonMessage.handleButtonAction(entity, 1, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 48, this.topPos + 153, 67, 20).build(builder -> new Button(builder) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HLACheckerProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_everyone", button_everyone);
		this.addRenderableWidget(button_everyone);
		button_specific_player = Button.builder(Component.translatable("gui.admin_panel.gamemode_changer.button_specific_player"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new GamemodeChangerButtonMessage(2, x, y, z, getEditBoxAndCheckBoxValues()));
				GamemodeChangerButtonMessage.handleButtonAction(entity, 2, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 32, this.topPos + 88, 103, 20).build();
		guistate.put("button:button_specific_player", button_specific_player);
		this.addRenderableWidget(button_specific_player);
		button_random_player = Button.builder(Component.translatable("gui.admin_panel.gamemode_changer.button_random_player"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new GamemodeChangerButtonMessage(3, x, y, z, getEditBoxAndCheckBoxValues()));
				GamemodeChangerButtonMessage.handleButtonAction(entity, 3, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 37, this.topPos + 57, 93, 20).build();
		guistate.put("button:button_random_player", button_random_player);
		this.addRenderableWidget(button_random_player);
	}
}
