package net.mod.adminpanel.procedures;

import net.mod.adminpanel.network.AdminPanelModVariables;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class HLAScriptProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		if (BoolArgumentType.getBool(arguments, "true_or_false") == true) {
			{
				AdminPanelModVariables.PlayerVariables _vars = (new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "username_of_target");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()).getData(AdminPanelModVariables.PLAYER_VARIABLES);
				_vars.High_Level_Administrator = true;
				_vars.syncPlayerVariables((new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "username_of_target");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()));
			}
			if ((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "username_of_target");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()) instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("High-level Administrator has been given to you, Press O to open the Admin Panel"), true);
		} else if (BoolArgumentType.getBool(arguments, "true_or_false") == false) {
			{
				AdminPanelModVariables.PlayerVariables _vars = (new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "username_of_target");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()).getData(AdminPanelModVariables.PLAYER_VARIABLES);
				_vars.High_Level_Administrator = false;
				_vars.syncPlayerVariables((new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "username_of_target");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()));
			}
			if ((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "username_of_target");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()) instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("High-level Administrator has been Revoked from You"), true);
		}
	}
}
