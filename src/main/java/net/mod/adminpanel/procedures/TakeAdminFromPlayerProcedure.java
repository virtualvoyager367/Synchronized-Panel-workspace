package net.mod.adminpanel.procedures;

import net.mod.adminpanel.network.AdminPanelModVariables;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class TakeAdminFromPlayerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(AdminPanelModVariables.PLAYER_VARIABLES).Has_Admin == true) {
			{
				AdminPanelModVariables.PlayerVariables _vars = entity.getData(AdminPanelModVariables.PLAYER_VARIABLES);
				_vars.Has_Admin = false;
				_vars.syncPlayerVariables(entity);
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Admin has been taken away from you, you can no longer open the Admin Panel"), false);
		}
	}
}
