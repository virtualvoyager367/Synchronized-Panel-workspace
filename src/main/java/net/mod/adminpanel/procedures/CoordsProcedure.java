package net.mod.adminpanel.procedures;

import net.mod.adminpanel.network.AdminPanelModVariables;

import net.minecraft.world.entity.Entity;

public class CoordsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(AdminPanelModVariables.PLAYER_VARIABLES).ShowCoords == false) {
			{
				AdminPanelModVariables.PlayerVariables _vars = entity.getData(AdminPanelModVariables.PLAYER_VARIABLES);
				_vars.ShowCoords = true;
				_vars.syncPlayerVariables(entity);
			}
			CoordsUpdateProcedure.execute(entity);
		} else {
			{
				AdminPanelModVariables.PlayerVariables _vars = entity.getData(AdminPanelModVariables.PLAYER_VARIABLES);
				_vars.ShowCoords = false;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
