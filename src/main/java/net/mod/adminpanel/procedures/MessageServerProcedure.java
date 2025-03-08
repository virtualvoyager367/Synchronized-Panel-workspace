package net.mod.adminpanel.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.HashMap;

public class MessageServerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, HashMap guistate) {
		if (guistate == null)
			return;
		String Bold_Text = "";
		String Command_conditions = "";
		String Obfuscated_Text = "";
		String italics_Text = "";
		String Color = "";
		String Text_For_Message_Output = "";
		if ((guistate.containsKey("checkboxin:Bold") && ((String) guistate.get("checkboxin:Bold")).equals("true") ? true : false) == true) {
			Bold_Text = "\"bold\":true";
		} else {
			Bold_Text = "\"bold\":false";
		}
		if ((guistate.containsKey("checkboxin:Obfuscated") && ((String) guistate.get("checkboxin:Obfuscated")).equals("true") ? true : false) == true) {
			Obfuscated_Text = "\"obfuscated\":true";
		} else {
			Obfuscated_Text = "\"obfuscated\":false";
		}
		if ((guistate.containsKey("checkboxin:Italic") && ((String) guistate.get("checkboxin:Italic")).equals("true") ? true : false) == true) {
			italics_Text = "\"italic\":true";
		} else {
			italics_Text = "\"italic\":false";
		}
		Text_For_Message_Output = "\"text\":\"" + "" + ((guistate.containsKey("textin:Text_For_Message") ? (String) guistate.get("textin:Text_For_Message") : "") + "" + "\"");
		Command_conditions = "{" + (Text_For_Message_Output + ""
				+ ("," + (Bold_Text + "" + ("," + (Obfuscated_Text + "" + ("," + ((italics_Text + "" + ("," + ("\"color\":\"" + "" + ((guistate.containsKey("textin:Color") ? (String) guistate.get("textin:Color") : "") + "" + "\"")))) + "}")))))));
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
					("/tellraw @a " + Command_conditions));
	}
}
