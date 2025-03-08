
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mod.adminpanel.init;

import org.lwjgl.glfw.GLFW;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mod.adminpanel.network.OpenAdminPanelMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class AdminPanelModKeyMappings {
	public static final KeyMapping OPEN_ADMIN_PANEL = new KeyMapping("key.admin_panel.open_admin_panel", GLFW.GLFW_KEY_O, "key.categories.gameplay") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PacketDistributor.sendToServer(new OpenAdminPanelMessage(0, 0));
				OpenAdminPanelMessage.pressAction(Minecraft.getInstance().player, 0, 0);
				OPEN_ADMIN_PANEL_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - OPEN_ADMIN_PANEL_LASTPRESS);
				PacketDistributor.sendToServer(new OpenAdminPanelMessage(1, dt));
				OpenAdminPanelMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	private static long OPEN_ADMIN_PANEL_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(OPEN_ADMIN_PANEL);
	}

	@EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(ClientTickEvent.Post event) {
			if (Minecraft.getInstance().screen == null) {
				OPEN_ADMIN_PANEL.consumeClick();
			}
		}
	}
}
