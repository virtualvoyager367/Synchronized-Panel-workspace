package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.MessageSettingsMenu;
import net.mod.adminpanel.network.MessageSettingsButtonMessage;
import net.mod.adminpanel.init.AdminPanelModScreens.WidgetScreen;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class MessageSettingsScreen extends AbstractContainerScreen<MessageSettingsMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = MessageSettingsMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox Color;
	EditBox Text_For_Message;
	Checkbox Bold;
	Checkbox Obfuscated;
	Checkbox Italic;
	Button button_send_message;

	public MessageSettingsScreen(MessageSettingsMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 233;
		this.imageHeight = 220;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof MessageSettingsScreen sc) {
			textstate.put("textin:Color", sc.Color.getValue());
			textstate.put("textin:Text_For_Message", sc.Text_For_Message.getValue());

			textstate.put("checkboxin:Bold", sc.Bold.selected() ? "true" : "false");
			textstate.put("checkboxin:Obfuscated", sc.Obfuscated.selected() ? "true" : "false");
			textstate.put("checkboxin:Italic", sc.Italic.selected() ? "true" : "false");
		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/message_settings.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		Color.render(guiGraphics, mouseX, mouseY, partialTicks);
		Text_For_Message.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (Color.isFocused())
			return Color.keyPressed(key, b, c);
		if (Text_For_Message.isFocused())
			return Text_For_Message.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String ColorValue = Color.getValue();
		String Text_For_MessageValue = Text_For_Message.getValue();
		super.resize(minecraft, width, height);
		Color.setValue(ColorValue);
		Text_For_Message.setValue(Text_For_MessageValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.message_settings.label_color"), 25, 182, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.message_settings.label_message_settings"), 79, 8, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.message_settings.label_text"), 28, 151, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		Color = new EditBox(this.font, this.leftPos + 9, this.topPos + 193, 118, 18, Component.translatable("gui.admin_panel.message_settings.Color")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.message_settings.Color").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.message_settings.Color").getString());
				else
					setSuggestion(null);
			}
		};
		Color.setMaxLength(32767);
		Color.setSuggestion(Component.translatable("gui.admin_panel.message_settings.Color").getString());
		guistate.put("text:Color", Color);
		this.addWidget(this.Color);
		Text_For_Message = new EditBox(this.font, this.leftPos + 9, this.topPos + 163, 118, 18, Component.translatable("gui.admin_panel.message_settings.Text_For_Message")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.message_settings.Text_For_Message").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.message_settings.Text_For_Message").getString());
				else
					setSuggestion(null);
			}
		};
		Text_For_Message.setMaxLength(32767);
		Text_For_Message.setSuggestion(Component.translatable("gui.admin_panel.message_settings.Text_For_Message").getString());
		guistate.put("text:Text_For_Message", Text_For_Message);
		this.addWidget(this.Text_For_Message);
		button_send_message = Button.builder(Component.translatable("gui.admin_panel.message_settings.button_send_message"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new MessageSettingsButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				MessageSettingsButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 139, this.topPos + 193, 87, 20).build();
		guistate.put("button:button_send_message", button_send_message);
		this.addRenderableWidget(button_send_message);
		Bold = Checkbox.builder(Component.translatable("gui.admin_panel.message_settings.Bold"), this.font).pos(this.leftPos + 17, this.topPos + 35)

				.build();
		guistate.put("checkbox:Bold", Bold);
		this.addRenderableWidget(Bold);
		Obfuscated = Checkbox.builder(Component.translatable("gui.admin_panel.message_settings.Obfuscated"), this.font).pos(this.leftPos + 17, this.topPos + 78)

				.build();
		guistate.put("checkbox:Obfuscated", Obfuscated);
		this.addRenderableWidget(Obfuscated);
		Italic = Checkbox.builder(Component.translatable("gui.admin_panel.message_settings.Italic"), this.font).pos(this.leftPos + 17, this.topPos + 119)

				.build();
		guistate.put("checkbox:Italic", Italic);
		this.addRenderableWidget(Italic);
	}
}
