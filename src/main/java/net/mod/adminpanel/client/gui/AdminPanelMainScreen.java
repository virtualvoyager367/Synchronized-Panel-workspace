package net.mod.adminpanel.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.mod.adminpanel.world.inventory.AdminPanelMainMenu;
import net.mod.adminpanel.procedures.HLACheckerProcedure;
import net.mod.adminpanel.network.AdminPanelMainButtonMessage;
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

public class AdminPanelMainScreen extends AbstractContainerScreen<AdminPanelMainMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = AdminPanelMainMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_kill;
	Button button_options;
	Button button_weather;
	Button button_time;
	Button button_tellraw;
	Button button_gamemode_changer;
	Button button_kill1;
	Button button_kick;
	Button button_ban;
	Button button_hla;
	Button button_warn_player;

	public AdminPanelMainScreen(AdminPanelMainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 192;
		this.imageHeight = 217;
	}

	public static HashMap<String, String> getEditBoxAndCheckBoxValues() {
		HashMap<String, String> textstate = new HashMap<>();
		if (Minecraft.getInstance().screen instanceof AdminPanelMainScreen sc) {

		}
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("admin_panel:textures/screens/admin_panel_main.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.admin_panel_main.label_admin_panel"), 65, 10, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.admin_panel.admin_panel_main.label_empty"), 4, 141, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_kill = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_kill"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(0, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 0, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 122, this.topPos + 82, 46, 20).build();
		guistate.put("button:button_kill", button_kill);
		this.addRenderableWidget(button_kill);
		button_options = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_options"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(1, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 1, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 4, this.topPos + 192, 61, 20).build();
		guistate.put("button:button_options", button_options);
		this.addRenderableWidget(button_options);
		button_weather = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_weather"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(2, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 2, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 113, this.topPos + 55, 61, 20).build();
		guistate.put("button:button_weather", button_weather);
		this.addRenderableWidget(button_weather);
		button_time = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_time"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(3, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 3, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 120, this.topPos + 30, 46, 20).build();
		guistate.put("button:button_time", button_time);
		this.addRenderableWidget(button_time);
		button_tellraw = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_tellraw"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(4, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 4, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 13, this.topPos + 108, 61, 20).build();
		guistate.put("button:button_tellraw", button_tellraw);
		this.addRenderableWidget(button_tellraw);
		button_gamemode_changer = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_gamemode_changer"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(5, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 5, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 37, this.topPos + 156, 108, 20).build();
		guistate.put("button:button_gamemode_changer", button_gamemode_changer);
		this.addRenderableWidget(button_gamemode_changer);
		button_kill1 = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_kill1"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(6, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 6, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 19, this.topPos + 29, 46, 20).build();
		guistate.put("button:button_kill1", button_kill1);
		this.addRenderableWidget(button_kill1);
		button_kick = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_kick"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(7, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 7, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 19, this.topPos + 55, 46, 20).build();
		guistate.put("button:button_kick", button_kick);
		this.addRenderableWidget(button_kick);
		button_ban = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_ban"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(8, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 8, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 22, this.topPos + 82, 40, 20).build();
		guistate.put("button:button_ban", button_ban);
		this.addRenderableWidget(button_ban);
		button_hla = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_hla"), e -> {
			if (HLACheckerProcedure.execute(entity)) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(9, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 9, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 147, this.topPos + 192, 40, 20).build(builder -> new Button(builder) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HLACheckerProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		});
		guistate.put("button:button_hla", button_hla);
		this.addRenderableWidget(button_hla);
		button_warn_player = Button.builder(Component.translatable("gui.admin_panel.admin_panel_main.button_warn_player"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AdminPanelMainButtonMessage(10, x, y, z, getEditBoxAndCheckBoxValues()));
				AdminPanelMainButtonMessage.handleButtonAction(entity, 10, x, y, z, getEditBoxAndCheckBoxValues());
			}
		}).bounds(this.leftPos + 103, this.topPos + 108, 82, 20).build();
		guistate.put("button:button_warn_player", button_warn_player);
		this.addRenderableWidget(button_warn_player);
	}
}
