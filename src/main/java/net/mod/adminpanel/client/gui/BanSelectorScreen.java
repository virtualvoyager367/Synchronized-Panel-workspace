package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.BanSelectorMenu;
import net.mod.adminpanel.network.BanSelectorButtonMessage;
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

public class BanSelectorScreen extends AbstractContainerScreen<BanSelectorMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = BanSelectorMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox BanPlayerName;
	EditBox BanPlayerReason;
	Button button_ban;

	public BanSelectorScreen(BanSelectorMenu container, Inventory inventory, Component text) {
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
		if (Minecraft.getInstance().screen instanceof BanSelectorScreen sc) {
			textstate.put("textin:BanPlayerName", sc.BanPlayerName.getValue());
			textstate.put("textin:BanPlayerReason", sc.BanPlayerReason.getValue());

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/ban_selector.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		BanPlayerName.render(guiGraphics, mouseX, mouseY, partialTicks);
		BanPlayerReason.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (BanPlayerName.isFocused())
			return BanPlayerName.keyPressed(key, b, c);
		if (BanPlayerReason.isFocused())
			return BanPlayerReason.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String BanPlayerNameValue = BanPlayerName.getValue();
		String BanPlayerReasonValue = BanPlayerReason.getValue();
		super.resize(minecraft, width, height);
		BanPlayerName.setValue(BanPlayerNameValue);
		BanPlayerReason.setValue(BanPlayerReasonValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.ban_selector.label_admin_panel_ban"), 25, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		BanPlayerName = new EditBox(this.font, this.leftPos + 9, this.topPos + 20, 118, 18, Component.translatable("gui.admin_panel.ban_selector.BanPlayerName")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.ban_selector.BanPlayerName").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.ban_selector.BanPlayerName").getString());
				else
					setSuggestion(null);
			}
		};
		BanPlayerName.setMaxLength(32767);
		BanPlayerName.setSuggestion(Component.translatable("gui.admin_panel.ban_selector.BanPlayerName").getString());
		guistate.put("text:BanPlayerName", BanPlayerName);
		this.addWidget(this.BanPlayerName);
		BanPlayerReason = new EditBox(this.font, this.leftPos + 9, this.topPos + 45, 118, 18, Component.translatable("gui.admin_panel.ban_selector.BanPlayerReason")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.ban_selector.BanPlayerReason").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.ban_selector.BanPlayerReason").getString());
				else
					setSuggestion(null);
			}
		};
		BanPlayerReason.setMaxLength(32767);
		BanPlayerReason.setSuggestion(Component.translatable("gui.admin_panel.ban_selector.BanPlayerReason").getString());
		guistate.put("text:BanPlayerReason", BanPlayerReason);
		this.addWidget(this.BanPlayerReason);
		button_ban = Button.builder(Component.translatable("gui.admin_panel.ban_selector.button_ban"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new BanSelectorButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				BanSelectorButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 46, this.topPos + 70, 40, 20).build();
		guistate.put("button:button_ban", button_ban);
		this.addRenderableWidget(button_ban);
	}
}
