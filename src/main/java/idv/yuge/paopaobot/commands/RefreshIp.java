package idv.yuge.paopaobot.commands;

import java.util.ArrayList;
import java.util.List;

import idv.yuge.paopaobot.runnable.RunnableIp;
import idv.yuge.paopaobot.util.ConsoleColors;
import idv.yuge.paopaobot.util.PrintOut;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class RefreshIp extends ListenerAdapter {
	
	
	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		event.deferReply(true).queue(); // Let the user know we received the command before doing anything else
		InteractionHook hook = event.getHook(); // This is a special webhook that allows you to send messages without having permissions in the channel and also allows ephemeral messages
        hook.setEphemeral(true); // All messages here will now be ephemeral implicitly
		RunnableIp.getIp();
		hook.sendMessage("IP刷新").queue();
		PrintOut.timePrintln(ConsoleColors.YELLOW_BOLD_BRIGHT, event.getUser().getName()+"使用指令:手動刷新IP");
	}

	@Override
	public void onGuildReady(GuildReadyEvent event) {
		List<CommandData> commandData = new ArrayList<>();
		commandData.add(Commands.slash("刷新ip", "手動刷新IP"));
		
		event.getGuild().updateCommands().addCommands(commandData).queue();
	}
	
}
