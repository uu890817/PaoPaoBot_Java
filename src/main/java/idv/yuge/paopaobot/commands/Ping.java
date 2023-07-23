package idv.yuge.paopaobot.commands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Ping extends ListenerAdapter{

	@Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
		if (e.getName().equals("ping")) {
            // 回覆 PING 值到使用者
            e.reply("Pong").queue();
            
        }
    }

	@Override
	public void onGuildReady(GuildReadyEvent e) {
		List<CommandData> commandData = new ArrayList<>();
		commandData.add(Commands.slash("ping", "回復Pong"));
		e.getGuild().updateCommands().addCommands(commandData).queue();
	
		
	
	}
}
