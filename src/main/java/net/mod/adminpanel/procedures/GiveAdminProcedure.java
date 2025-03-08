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

public class GiveAdminProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		if (BoolArgumentType.getBool(arguments, "True_or_False") == true) {
			{
				AdminPanelModVariables.PlayerVariables _vars = (new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Username_Of_Reciever");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()).getData(AdminPanelModVariables.PLAYER_VARIABLES);
				_vars.Has_Admin = true;
				_vars.syncPlayerVariables((new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Username_Of_Reciever");
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
						return EntityArgument.getEntity(arguments, "Username_Of_Reciever");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()) instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Administrator has been given to you, Press O to open the Admin Panel"), true);
		} else if (BoolArgumentType.getBool(arguments, "True_or_False") == false) {
			{
				AdminPanelModVariables.PlayerVariables _vars = (new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Username_Of_Reciever");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()).getData(AdminPanelModVariables.PLAYER_VARIABLES);
				_vars.Has_Admin = false;
				_vars.syncPlayerVariables((new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Username_Of_Reciever");
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
						return EntityArgument.getEntity(arguments, "Username_Of_Reciever");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()) instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Administrator has been Revoked from you, You can no longer open the admin panel"), true);
		}
	}
}
