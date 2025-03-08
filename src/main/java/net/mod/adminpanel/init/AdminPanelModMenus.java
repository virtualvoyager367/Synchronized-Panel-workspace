
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mod.adminpanel.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.mod.adminpanel.world.inventory.WeatherPanelMenu;
import net.mod.adminpanel.world.inventory.VerifyShutdownMenu;
import net.mod.adminpanel.world.inventory.TimesetMenu;
import net.mod.adminpanel.world.inventory.TPMenu;
import net.mod.adminpanel.world.inventory.ServerOnlyMenu;
import net.mod.adminpanel.world.inventory.PlayerWarningMenuMenu;
import net.mod.adminpanel.world.inventory.MessageSettingsMenu;
import net.mod.adminpanel.world.inventory.KillSelectorMenu;
import net.mod.adminpanel.world.inventory.KickSelectorMenu;
import net.mod.adminpanel.world.inventory.HLAServerSettingsMenu;
import net.mod.adminpanel.world.inventory.HLAMenuMenu;
import net.mod.adminpanel.world.inventory.GamemodeYouChangerMenu;
import net.mod.adminpanel.world.inventory.GamemodeSpecificChangerMenu;
import net.mod.adminpanel.world.inventory.GamemodeRandomChangerMenu;
import net.mod.adminpanel.world.inventory.GamemodeEveryoneChangerMenu;
import net.mod.adminpanel.world.inventory.GamemodeChangerMenu;
import net.mod.adminpanel.world.inventory.BanSelectorMenu;
import net.mod.adminpanel.world.inventory.AdminpanelPersonalMenu;
import net.mod.adminpanel.world.inventory.AdminPanelSettingMenu;
import net.mod.adminpanel.world.inventory.AdminPanelMainMenu;
import net.mod.adminpanel.world.inventory.AdminPanelGamemodeMenu;
import net.mod.adminpanel.AdminPanelMod;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.registries.Registries;

import javax.annotation.Nullable;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class AdminPanelModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, AdminPanelMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<AdminPanelGamemodeMenu>> ADMIN_PANEL_GAMEMODE = REGISTRY.register("admin_panel_gamemode", () -> IMenuTypeExtension.create(AdminPanelGamemodeMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AdminpanelPersonalMenu>> ADMINPANEL_PERSONAL = REGISTRY.register("adminpanel_personal", () -> IMenuTypeExtension.create(AdminpanelPersonalMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AdminPanelSettingMenu>> ADMIN_PANEL_SETTING = REGISTRY.register("admin_panel_setting", () -> IMenuTypeExtension.create(AdminPanelSettingMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AdminPanelMainMenu>> ADMIN_PANEL_MAIN = REGISTRY.register("admin_panel_main", () -> IMenuTypeExtension.create(AdminPanelMainMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<TPMenu>> TP = REGISTRY.register("tp", () -> IMenuTypeExtension.create(TPMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<WeatherPanelMenu>> WEATHER_PANEL = REGISTRY.register("weather_panel", () -> IMenuTypeExtension.create(WeatherPanelMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<VerifyShutdownMenu>> VERIFY_SHUTDOWN = REGISTRY.register("verify_shutdown", () -> IMenuTypeExtension.create(VerifyShutdownMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<TimesetMenu>> TIMESET = REGISTRY.register("timeset", () -> IMenuTypeExtension.create(TimesetMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<MessageSettingsMenu>> MESSAGE_SETTINGS = REGISTRY.register("message_settings", () -> IMenuTypeExtension.create(MessageSettingsMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ServerOnlyMenu>> SERVER_ONLY = REGISTRY.register("server_only", () -> IMenuTypeExtension.create(ServerOnlyMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GamemodeChangerMenu>> GAMEMODE_CHANGER = REGISTRY.register("gamemode_changer", () -> IMenuTypeExtension.create(GamemodeChangerMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GamemodeYouChangerMenu>> GAMEMODE_YOU_CHANGER = REGISTRY.register("gamemode_you_changer", () -> IMenuTypeExtension.create(GamemodeYouChangerMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GamemodeEveryoneChangerMenu>> GAMEMODE_EVERYONE_CHANGER = REGISTRY.register("gamemode_everyone_changer", () -> IMenuTypeExtension.create(GamemodeEveryoneChangerMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GamemodeSpecificChangerMenu>> GAMEMODE_SPECIFIC_CHANGER = REGISTRY.register("gamemode_specific_changer", () -> IMenuTypeExtension.create(GamemodeSpecificChangerMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GamemodeRandomChangerMenu>> GAMEMODE_RANDOM_CHANGER = REGISTRY.register("gamemode_random_changer", () -> IMenuTypeExtension.create(GamemodeRandomChangerMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<KillSelectorMenu>> KILL_SELECTOR = REGISTRY.register("kill_selector", () -> IMenuTypeExtension.create(KillSelectorMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<KickSelectorMenu>> KICK_SELECTOR = REGISTRY.register("kick_selector", () -> IMenuTypeExtension.create(KickSelectorMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BanSelectorMenu>> BAN_SELECTOR = REGISTRY.register("ban_selector", () -> IMenuTypeExtension.create(BanSelectorMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<HLAMenuMenu>> HLA_MENU = REGISTRY.register("hla_menu", () -> IMenuTypeExtension.create(HLAMenuMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<HLAServerSettingsMenu>> HLA_SERVER_SETTINGS = REGISTRY.register("hla_server_settings", () -> IMenuTypeExtension.create(HLAServerSettingsMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PlayerWarningMenuMenu>> PLAYER_WARNING_MENU = REGISTRY.register("player_warning_menu", () -> IMenuTypeExtension.create(PlayerWarningMenuMenu::new));

	public static void setText(String boxname, String value, @Nullable ServerPlayer player) {
		if (player != null) {
			PacketDistributor.sendToPlayer(player, new GuiSyncMessage(boxname, value));
		} else {
			PacketDistributor.sendToAllPlayers(new GuiSyncMessage(boxname, value));
		}
	}

	public static record GuiSyncMessage(String editbox, String value) implements CustomPacketPayload {
		public static final Type<GuiSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AdminPanelMod.MODID, "gui_sync"));
		public static final StreamCodec<RegistryFriendlyByteBuf, GuiSyncMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, GuiSyncMessage message) -> {
			ComponentSerialization.TRUSTED_STREAM_CODEC.encode(buffer, Component.literal(message.editbox));
			ComponentSerialization.TRUSTED_STREAM_CODEC.encode(buffer, Component.literal(message.value));
		}, (RegistryFriendlyByteBuf buffer) -> {
			String editbox = ComponentSerialization.TRUSTED_STREAM_CODEC.decode(buffer).getString();
			String value = ComponentSerialization.TRUSTED_STREAM_CODEC.decode(buffer).getString();
			return new GuiSyncMessage(editbox, value);
		});

		@Override
		public Type<GuiSyncMessage> type() {
			return TYPE;
		}

		public static void handleData(final GuiSyncMessage message, final IPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND) {
				context.enqueueWork(() -> {
					AdminPanelModScreens.handleTextBoxMessage(message);
				}).exceptionally(e -> {
					context.connection().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		AdminPanelMod.addNetworkMessage(GuiSyncMessage.TYPE, GuiSyncMessage.STREAM_CODEC, GuiSyncMessage::handleData);
	}
}
