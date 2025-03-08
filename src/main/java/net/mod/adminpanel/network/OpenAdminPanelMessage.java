
package net.mod.adminpanel.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.mod.adminpanel.procedures.OpenAdminPanelCodeProcedure;
import net.mod.adminpanel.AdminPanelMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record OpenAdminPanelMessage(int eventType, int pressedms) implements CustomPacketPayload {
	public static final Type<OpenAdminPanelMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AdminPanelMod.MODID, "key_open_admin_panel"));
	public static final StreamCodec<RegistryFriendlyByteBuf, OpenAdminPanelMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, OpenAdminPanelMessage message) -> {
		buffer.writeInt(message.eventType);
		buffer.writeInt(message.pressedms);
	}, (RegistryFriendlyByteBuf buffer) -> new OpenAdminPanelMessage(buffer.readInt(), buffer.readInt()));

	@Override
	public Type<OpenAdminPanelMessage> type() {
		return TYPE;
	}

	public static void handleData(final OpenAdminPanelMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				pressAction(context.player(), message.eventType, message.pressedms);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;
		if (type == 0) {

			OpenAdminPanelCodeProcedure.execute(world, x, y, z, entity);
		}
		if (type == 1) {

			OpenAdminPanelCodeProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AdminPanelMod.addNetworkMessage(OpenAdminPanelMessage.TYPE, OpenAdminPanelMessage.STREAM_CODEC, OpenAdminPanelMessage::handleData);
	}
}
