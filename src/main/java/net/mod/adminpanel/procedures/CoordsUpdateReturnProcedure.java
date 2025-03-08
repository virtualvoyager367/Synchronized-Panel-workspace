package net.mod.adminpanel.procedures;

import net.minecraft.world.entity.Entity;

public class CoordsUpdateReturnProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return (" X: " + Math.round(entity.getX())) + "" + ((" Y: " + Math.round(entity.getY())) + "" + (" Z: " + Math.round(entity.getZ())));
	}
}
