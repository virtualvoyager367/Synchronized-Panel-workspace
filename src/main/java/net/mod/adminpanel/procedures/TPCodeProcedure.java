package net.mod.adminpanel.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.HashMap;

public class TPCodeProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		{
			Entity _ent = entity;
			_ent.teleportTo(new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(guistate.containsKey("textin:xcoords") ? (String) guistate.get("textin:xcoords") : ""), new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(guistate.containsKey("textin:ycoords") ? (String) guistate.get("textin:ycoords") : ""), new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(guistate.containsKey("textin:zcoords") ? (String) guistate.get("textin:zcoords") : ""));
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport(new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("textin:xcoords") ? (String) guistate.get("textin:xcoords") : ""), new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("textin:ycoords") ? (String) guistate.get("textin:ycoords") : ""), new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("textin:zcoords") ? (String) guistate.get("textin:zcoords") : ""), _ent.getYRot(), _ent.getXRot());
		}
		{
			Entity _ent = entity;
			if (!_ent.level().isClientSide() && _ent.getServer() != null) {
				_ent.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(),
								_ent.level().getServer(), _ent),
						(("tp " + (guistate.containsKey("textin:TPPlayername") ? (String) guistate.get("textin:TPPlayername") : "")) + "" + ((" " + ((guistate.containsKey("textin:xcoords") ? (String) guistate.get("textin:xcoords") : "") + " ")) + ""
								+ (((guistate.containsKey("textin:ycoords") ? (String) guistate.get("textin:ycoords") : "") + " ") + "" + (guistate.containsKey("textin:zcoords") ? (String) guistate.get("textin:zcoords") : "")))));
			}
		}
	}
}
