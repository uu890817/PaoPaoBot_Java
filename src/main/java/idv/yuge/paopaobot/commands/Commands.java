package idv.yuge.paopaobot.commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		super.onSlashCommandInteraction(event);
	}

	@Override
	public void onGuildReady(GuildReadyEvent event) {
		super.onGuildReady(event);
	}

	
}
