package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.KillSelectorMenu;
import net.mod.adminpanel.network.KillSelectorButtonMessage;
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

public class KillSelectorScreen extends AbstractContainerScreen<KillSelectorMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = KillSelectorMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox KillPlayerName;
	Button button_kill;

	public KillSelectorScreen(KillSelectorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 136;
		this.imageHeight = 70;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof KillSelectorScreen sc) {
			textstate.put("textin:KillPlayerName", sc.KillPlayerName.getValue());

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/kill_selector.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		KillPlayerName.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (KillPlayerName.isFocused())
			return KillPlayerName.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String KillPlayerNameValue = KillPlayerName.getValue();
		super.resize(minecraft, width, height);
		KillPlayerName.setValue(KillPlayerNameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.kill_selector.label_admin_panel_kill"), 24, 4, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		KillPlayerName = new EditBox(this.font, this.leftPos + 9, this.topPos + 19, 118, 18, Component.translatable("gui.admin_panel.kill_selector.KillPlayerName")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.kill_selector.KillPlayerName").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.kill_selector.KillPlayerName").getString());
				else
					setSuggestion(null);
			}
		};
		KillPlayerName.setMaxLength(32767);
		KillPlayerName.setSuggestion(Component.translatable("gui.admin_panel.kill_selector.KillPlayerName").getString());
		guistate.put("text:KillPlayerName", KillPlayerName);
		this.addWidget(this.KillPlayerName);
		button_kill = Button.builder(Component.translatable("gui.admin_panel.kill_selector.button_kill"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new KillSelectorButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				KillSelectorButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 44, this.topPos + 44, 46, 20).build();
		guistate.put("button:button_kill", button_kill);
		this.addRenderableWidget(button_kill);
	}
}
