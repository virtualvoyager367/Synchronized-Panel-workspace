package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.PlayerWarningMenuMenu;
import net.mod.adminpanel.network.PlayerWarningMenuButtonMessage;
import net.mod.adminpanel.init.AdminPanelModScreens.WidgetScreen;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class PlayerWarningMenuScreen extends AbstractContainerScreen<PlayerWarningMenuMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = PlayerWarningMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox TargetWarnedPlayer;
	EditBox TargetWarnedReason;
	Button button_send_warning;

	public PlayerWarningMenuScreen(PlayerWarningMenuMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 120;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof PlayerWarningMenuScreen sc) {
			textstate.put("textin:TargetWarnedPlayer", sc.TargetWarnedPlayer.getValue());
			textstate.put("textin:TargetWarnedReason", sc.TargetWarnedReason.getValue());

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/player_warning_menu.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		TargetWarnedPlayer.render(guiGraphics, mouseX, mouseY, partialTicks);
		TargetWarnedReason.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (TargetWarnedPlayer.isFocused())
			return TargetWarnedPlayer.keyPressed(key, b, c);
		if (TargetWarnedReason.isFocused())
			return TargetWarnedReason.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String TargetWarnedPlayerValue = TargetWarnedPlayer.getValue();
		String TargetWarnedReasonValue = TargetWarnedReason.getValue();
		super.resize(minecraft, width, height);
		TargetWarnedPlayer.setValue(TargetWarnedPlayerValue);
		TargetWarnedReason.setValue(TargetWarnedReasonValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.player_warning_menu.label_admin_panel_player_warning"), 15, 4, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		TargetWarnedPlayer = new EditBox(this.font, this.leftPos + 27, this.topPos + 32, 118, 18, Component.translatable("gui.admin_panel.player_warning_menu.TargetWarnedPlayer")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.player_warning_menu.TargetWarnedPlayer").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.player_warning_menu.TargetWarnedPlayer").getString());
				else
					setSuggestion(null);
			}
		};
		TargetWarnedPlayer.setMaxLength(32767);
		TargetWarnedPlayer.setSuggestion(Component.translatable("gui.admin_panel.player_warning_menu.TargetWarnedPlayer").getString());
		guistate.put("text:TargetWarnedPlayer", TargetWarnedPlayer);
		this.addWidget(this.TargetWarnedPlayer);
		TargetWarnedReason = new EditBox(this.font, this.leftPos + 27, this.topPos + 59, 118, 18, Component.translatable("gui.admin_panel.player_warning_menu.TargetWarnedReason")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.player_warning_menu.TargetWarnedReason").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.player_warning_menu.TargetWarnedReason").getString());
				else
					setSuggestion(null);
			}
		};
		TargetWarnedReason.setMaxLength(32767);
		TargetWarnedReason.setSuggestion(Component.translatable("gui.admin_panel.player_warning_menu.TargetWarnedReason").getString());
		guistate.put("text:TargetWarnedReason", TargetWarnedReason);
		this.addWidget(this.TargetWarnedReason);
		button_send_warning = Button.builder(Component.translatable("gui.admin_panel.player_warning_menu.button_send_warning"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new PlayerWarningMenuButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				PlayerWarningMenuButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 45, this.topPos + 88, 87, 20).build();
		guistate.put("button:button_send_warning", button_send_warning);
		this.addRenderableWidget(button_send_warning);
	}
}
