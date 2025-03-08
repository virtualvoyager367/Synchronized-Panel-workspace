package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.TPMenu;
import net.mod.adminpanel.network.TPButtonMessage;
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

public class TPScreen extends AbstractContainerScreen<TPMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = TPMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox xcoords;
	EditBox ycoords;
	EditBox zcoords;
	EditBox TPPlayername;
	Button button_tp;

	public TPScreen(TPMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 149;
		this.imageHeight = 159;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof TPScreen sc) {
			textstate.put("textin:xcoords", sc.xcoords.getValue());
			textstate.put("textin:ycoords", sc.ycoords.getValue());
			textstate.put("textin:zcoords", sc.zcoords.getValue());
			textstate.put("textin:TPPlayername", sc.TPPlayername.getValue());

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/tp.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		xcoords.render(guiGraphics, mouseX, mouseY, partialTicks);
		ycoords.render(guiGraphics, mouseX, mouseY, partialTicks);
		zcoords.render(guiGraphics, mouseX, mouseY, partialTicks);
		TPPlayername.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 7 && mouseX < leftPos + 31 && mouseY > topPos + 127 && mouseY < topPos + 151)
			guiGraphics.renderTooltip(font, Component.translatable("gui.admin_panel.tp.tooltip_do_not_have_x_y_or_z_at_the"), mouseX, mouseY);
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
		if (xcoords.isFocused())
			return xcoords.keyPressed(key, b, c);
		if (ycoords.isFocused())
			return ycoords.keyPressed(key, b, c);
		if (zcoords.isFocused())
			return zcoords.keyPressed(key, b, c);
		if (TPPlayername.isFocused())
			return TPPlayername.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String xcoordsValue = xcoords.getValue();
		String ycoordsValue = ycoords.getValue();
		String zcoordsValue = zcoords.getValue();
		String TPPlayernameValue = TPPlayername.getValue();
		super.resize(minecraft, width, height);
		xcoords.setValue(xcoordsValue);
		ycoords.setValue(ycoordsValue);
		zcoords.setValue(zcoordsValue);
		TPPlayername.setValue(TPPlayernameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.tp.label_admin_panel_tp"), 36, 7, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		xcoords = new EditBox(this.font, this.leftPos + 16, this.topPos + 18, 118, 18, Component.translatable("gui.admin_panel.tp.xcoords")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.tp.xcoords").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.tp.xcoords").getString());
				else
					setSuggestion(null);
			}
		};
		xcoords.setMaxLength(32767);
		xcoords.setSuggestion(Component.translatable("gui.admin_panel.tp.xcoords").getString());
		guistate.put("text:xcoords", xcoords);
		this.addWidget(this.xcoords);
		ycoords = new EditBox(this.font, this.leftPos + 16, this.topPos + 43, 118, 18, Component.translatable("gui.admin_panel.tp.ycoords")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.tp.ycoords").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.tp.ycoords").getString());
				else
					setSuggestion(null);
			}
		};
		ycoords.setMaxLength(32767);
		ycoords.setSuggestion(Component.translatable("gui.admin_panel.tp.ycoords").getString());
		guistate.put("text:ycoords", ycoords);
		this.addWidget(this.ycoords);
		zcoords = new EditBox(this.font, this.leftPos + 16, this.topPos + 70, 118, 18, Component.translatable("gui.admin_panel.tp.zcoords")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.tp.zcoords").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.tp.zcoords").getString());
				else
					setSuggestion(null);
			}
		};
		zcoords.setMaxLength(32767);
		zcoords.setSuggestion(Component.translatable("gui.admin_panel.tp.zcoords").getString());
		guistate.put("text:zcoords", zcoords);
		this.addWidget(this.zcoords);
		TPPlayername = new EditBox(this.font, this.leftPos + 16, this.topPos + 107, 118, 18, Component.translatable("gui.admin_panel.tp.TPPlayername")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.tp.TPPlayername").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.admin_panel.tp.TPPlayername").getString());
				else
					setSuggestion(null);
			}
		};
		TPPlayername.setMaxLength(32767);
		TPPlayername.setSuggestion(Component.translatable("gui.admin_panel.tp.TPPlayername").getString());
		guistate.put("text:TPPlayername", TPPlayername);
		this.addWidget(this.TPPlayername);
		button_tp = Button.builder(Component.translatable("gui.admin_panel.tp.button_tp"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TPButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				TPButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 57, this.topPos + 129, 35, 20).build();
		guistate.put("button:button_tp", button_tp);
		this.addRenderableWidget(button_tp);
	}
}
