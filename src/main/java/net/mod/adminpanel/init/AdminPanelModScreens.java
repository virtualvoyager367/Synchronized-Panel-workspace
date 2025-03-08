
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mod.adminpanel.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mod.adminpanel.init.AdminPanelModMenus.GuiSyncMessage;
import net.mod.adminpanel.client.gui.WeatherPanelScreen;
import net.mod.adminpanel.client.gui.VerifyShutdownScreen;
import net.mod.adminpanel.client.gui.TimesetScreen;
import net.mod.adminpanel.client.gui.TPScreen;
import net.mod.adminpanel.client.gui.ServerOnlyScreen;
import net.mod.adminpanel.client.gui.PlayerWarningMenuScreen;
import net.mod.adminpanel.client.gui.MessageSettingsScreen;
import net.mod.adminpanel.client.gui.KillSelectorScreen;
import net.mod.adminpanel.client.gui.KickSelectorScreen;
import net.mod.adminpanel.client.gui.HLAServerSettingsScreen;
import net.mod.adminpanel.client.gui.HLAMenuScreen;
import net.mod.adminpanel.client.gui.GamemodeYouChangerScreen;
import net.mod.adminpanel.client.gui.GamemodeSpecificChangerScreen;
import net.mod.adminpanel.client.gui.GamemodeRandomChangerScreen;
import net.mod.adminpanel.client.gui.GamemodeEveryoneChangerScreen;
import net.mod.adminpanel.client.gui.GamemodeChangerScreen;
import net.mod.adminpanel.client.gui.BanSelectorScreen;
import net.mod.adminpanel.client.gui.AdminpanelPersonalScreen;
import net.mod.adminpanel.client.gui.AdminPanelSettingScreen;
import net.mod.adminpanel.client.gui.AdminPanelMainScreen;
import net.mod.adminpanel.client.gui.AdminPanelGamemodeScreen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AdminPanelModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(AdminPanelModMenus.ADMIN_PANEL_GAMEMODE.get(), AdminPanelGamemodeScreen::new);
		event.register(AdminPanelModMenus.ADMINPANEL_PERSONAL.get(), AdminpanelPersonalScreen::new);
		event.register(AdminPanelModMenus.ADMIN_PANEL_SETTING.get(), AdminPanelSettingScreen::new);
		event.register(AdminPanelModMenus.ADMIN_PANEL_MAIN.get(), AdminPanelMainScreen::new);
		event.register(AdminPanelModMenus.TP.get(), TPScreen::new);
		event.register(AdminPanelModMenus.WEATHER_PANEL.get(), WeatherPanelScreen::new);
		event.register(AdminPanelModMenus.VERIFY_SHUTDOWN.get(), VerifyShutdownScreen::new);
		event.register(AdminPanelModMenus.TIMESET.get(), TimesetScreen::new);
		event.register(AdminPanelModMenus.MESSAGE_SETTINGS.get(), MessageSettingsScreen::new);
		event.register(AdminPanelModMenus.SERVER_ONLY.get(), ServerOnlyScreen::new);
		event.register(AdminPanelModMenus.GAMEMODE_CHANGER.get(), GamemodeChangerScreen::new);
		event.register(AdminPanelModMenus.GAMEMODE_YOU_CHANGER.get(), GamemodeYouChangerScreen::new);
		event.register(AdminPanelModMenus.GAMEMODE_EVERYONE_CHANGER.get(), GamemodeEveryoneChangerScreen::new);
		event.register(AdminPanelModMenus.GAMEMODE_SPECIFIC_CHANGER.get(), GamemodeSpecificChangerScreen::new);
		event.register(AdminPanelModMenus.GAMEMODE_RANDOM_CHANGER.get(), GamemodeRandomChangerScreen::new);
		event.register(AdminPanelModMenus.KILL_SELECTOR.get(), KillSelectorScreen::new);
		event.register(AdminPanelModMenus.KICK_SELECTOR.get(), KickSelectorScreen::new);
		event.register(AdminPanelModMenus.BAN_SELECTOR.get(), BanSelectorScreen::new);
		event.register(AdminPanelModMenus.HLA_MENU.get(), HLAMenuScreen::new);
		event.register(AdminPanelModMenus.HLA_SERVER_SETTINGS.get(), HLAServerSettingsScreen::new);
		event.register(AdminPanelModMenus.PLAYER_WARNING_MENU.get(), PlayerWarningMenuScreen::new);
	}

	static void handleTextBoxMessage(GuiSyncMessage message) {
		String editbox = message.editbox();
		String value = message.value();
		Screen currentScreen = Minecraft.getInstance().screen;
		if (currentScreen instanceof WidgetScreen sc) {
			HashMap<String, Object> widgets = sc.getWidgets();
			Object obj = widgets.get("text:" + editbox);
			if (obj instanceof EditBox box) {
				box.setValue(value);
			}
		}
	}

	public interface WidgetScreen {
		HashMap<String, Object> getWidgets();
	}
}
