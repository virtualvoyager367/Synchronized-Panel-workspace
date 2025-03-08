package net.mod.adminpanel.procedures;

import net.mod.adminpanel.network.AdminPanelModVariables;

import net.minecraft.world.entity.Entity;

public class HLACheckerProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity.getData(AdminPanelModVariables.PLAYER_VARIABLES).High_Level_Administrator == true) {
			return true;
		}
		return false;
	}
}
