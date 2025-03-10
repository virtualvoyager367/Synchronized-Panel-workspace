package net.mod.adminpanel.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.HashMap;

public class WarnPlayerScriptProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, HashMap guistate) {
		if (guistate == null)
			return;
		String Reason = "";
		Reason = "\"text\":\"" + "" + ("Moderator Warning: " + (guistate.containsKey("textin:TargetWarnedReason") ? (String) guistate.get("textin:TargetWarnedReason") : "") + "\",");
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
					("/tellraw " + (guistate.containsKey("textin:TargetWarnedPlayer") ? (String) guistate.get("textin:TargetWarnedPlayer") : "") + " " + "{" + Reason + "\"color\":" + "\"red\"," + "\"bold\":" + "true" + "}"));
	}
}
