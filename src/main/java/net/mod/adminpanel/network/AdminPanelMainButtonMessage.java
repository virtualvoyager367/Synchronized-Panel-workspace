
package net.mod.adminpanel.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.mod.adminpanel.world.inventory.AdminPanelMainMenu;
import net.mod.adminpanel.procedures.OpentimeProcedure;
import net.mod.adminpanel.procedures.OpenWeatherProcedure;
import net.mod.adminpanel.procedures.OpenTPProcedure;
import net.mod.adminpanel.procedures.OpenSettingsProcedure;
import net.mod.adminpanel.procedures.OpenPlayerWarnProcedure;
import net.mod.adminpanel.procedures.OpenMessageServerProcedure;
import net.mod.adminpanel.procedures.OpenKillSelectorProcedure;
import net.mod.adminpanel.procedures.OpenKickSelectorProcedure;
import net.mod.adminpanel.procedures.OpenHLAServerSettingsProcedure;
import net.mod.adminpanel.procedures.OpenGamemodeChangerProcedure;
import net.mod.adminpanel.procedures.OpenBanSelectorProcedure;
import net.mod.adminpanel.AdminPanelMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.Map;
import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record AdminPanelMainButtonMessage(int buttonID, int x, int y, int z, HashMap<String, String> textstate) implements CustomPacketPayload {

	public static final Type<AdminPanelMainButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AdminPanelMod.MODID, "admin_panel_main_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, AdminPanelMainButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, AdminPanelMainButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		writeTextState(message.textstate, buffer);
	}, (RegistryFriendlyByteBuf buffer) -> new AdminPanelMainButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), readTextState(buffer)));
	@Override
	public Type<AdminPanelMainButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final AdminPanelMainButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				HashMap<String, String> textstate = message.textstate;
				handleButtonAction(entity, buttonID, x, y, z, textstate);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z, HashMap<String, String> textstate) {
		Level world = entity.level();
		HashMap guistate = AdminPanelMainMenu.guistate;
		// connect EditBox and CheckBox to guistate
		for (Map.Entry<String, String> entry : textstate.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			guistate.put(key, value);
		}
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			OpenTPProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			OpenSettingsProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			OpenWeatherProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			OpentimeProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			OpenMessageServerProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			OpenGamemodeChangerProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			OpenKillSelectorProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			OpenKickSelectorProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 8) {

			OpenBanSelectorProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 9) {

			OpenHLAServerSettingsProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 10) {

			OpenPlayerWarnProcedure.execute(world, x, y, z, entity);
		}
	}

	private static void writeTextState(HashMap<String, String> map, RegistryFriendlyByteBuf buffer) {
		buffer.writeInt(map.size());
		for (Map.Entry<String, String> entry : map.entrySet()) {
			writeComponent(buffer, Component.literal(entry.getKey()));
			writeComponent(buffer, Component.literal(entry.getValue()));
		}
	}

	private static HashMap<String, String> readTextState(RegistryFriendlyByteBuf buffer) {
		int size = buffer.readInt();
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String key = readComponent(buffer).getString();
			String value = readComponent(buffer).getString();
			map.put(key, value);
		}
		return map;
	}

	private static Component readComponent(RegistryFriendlyByteBuf buffer) {
		return ComponentSerialization.TRUSTED_STREAM_CODEC.decode(buffer);
	}

	private static void writeComponent(RegistryFriendlyByteBuf buffer, Component component) {
		ComponentSerialization.TRUSTED_STREAM_CODEC.encode(buffer, component);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AdminPanelMod.addNetworkMessage(AdminPanelMainButtonMessage.TYPE, AdminPanelMainButtonMessage.STREAM_CODEC, AdminPanelMainButtonMessage::handleData);
	}
}
