package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.KickSelectorMenu;
import net.mod.adminpanel.network.KickSelectorButtonMessage;
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

public class KickSelectorScreen extends AbstractContainerScreen<KickSelectorMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = KickSelectorMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox KickPlayerName;
	EditBox KickPlayerReason;
	Button button_kick;

	public KickSelectorScreen(KickSelectorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 136;
		this.imageHeight = 100;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof KickSelectorScreen sc) {
			textstate.put("textin:KickPlayerName", sc.KickPlayerName.getValue());
			textstate.put("textin:KickPlayerReason", sc.KickPlayerReason.getValue());

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/kick_selector.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		KickPlayerName.render(guiGraphics, mouseX, mouseY, partialTicks);
		KickPlayerReason.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (KickPlayerName.isFocused())
			return KickPlayerName.keyPressed(key, b, c);
		if (KickPlayerReason.isFocused())
			return KickPlayerReason.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String KickPlayerNameValue = KickPlayerName.getValue();
		String KickPlayerReasonValue = KickPlayerReason.getValue();
		super.resize(minecraft, width, height);
		KickPlayerName.setValue(KickPlayerNameValue);
		KickPlayerReason.setValue(KickPlayerReasonValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.kick_selector.label_admin_panel_kick"), 22, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		KickPlayerName = new EditBox(this.font, this.leftPos + 9, this.topPos + 19, 118, 18, Component.translatable("gui.admin_panel.kick_selector.KickPlayerName")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.kick_selector.KickPlayerName").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.kick_selector.KickPlayerName").getString());
				else
					setSuggestion(null);
			}
		};
		KickPlayerName.setMaxLength(32767);
		KickPlayerName.setSuggestion(Component.translatable("gui.admin_panel.kick_selector.KickPlayerName").getString());
		guistate.put("text:KickPlayerName", KickPlayerName);
		this.addWidget(this.KickPlayerName);
		KickPlayerReason = new EditBox(this.font, this.leftPos + 9, this.topPos + 49, 118, 18, Component.translatable("gui.admin_panel.kick_selector.KickPlayerReason")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.kick_selector.KickPlayerReason").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.kick_selector.KickPlayerReason").getString());
				else
					setSuggestion(null);
			}
		};
		KickPlayerReason.setMaxLength(32767);
		KickPlayerReason.setSuggestion(Component.translatable("gui.admin_panel.kick_selector.KickPlayerReason").getString());
		guistate.put("text:KickPlayerReason", KickPlayerReason);
		this.addWidget(this.KickPlayerReason);
		button_kick = Button.builder(Component.translatable("gui.admin_panel.kick_selector.button_kick"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new KickSelectorButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				KickSelectorButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 44, this.topPos + 73, 46, 20).build();
		guistate.put("button:button_kick", button_kick);
		this.addRenderableWidget(button_kick);
	}
}
