package idv.yuge.paopao.discordbot;

import java.io.IOException;

import idv.yuge.paopao.hardware.HardwareInfo;
import idv.yuge.paopao.mcservercontroller.ServerBackup;
import idv.yuge.paopao.mcservercontroller.ServerController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class SlashCommands extends ListenerAdapter {

	public void setSlachCommands() {
		JDA bot = DiscordBotFactory.getBot();
		bot.updateCommands().addCommands(
				Commands.slash("你還活著嗎", "確認機器人狀態"), 
				Commands.slash("取得記憶體用量", "取得記憶體用量"),
				Commands.slash("開啟伺服器", "啟動Minecraft伺服器"),
				Commands.slash("關閉伺服器", "關閉Minecraft伺服器"), 
				Commands.slash("備份伺服器", "備份Minecraft伺服器"),
				Commands.slash("發送訊息到伺服器", "發送訊息喔").addOption(OptionType.STRING, "訊息", "要發送的訊息"),
				Commands.slash("發送指令到伺服器", "發送指令喔").addOption(OptionType.STRING, "指令", "要發送的指令"),
				Commands.slash("強制關閉伺服器線程", "危險!!! 可能會造成地圖損壞").addOption(OptionType.STRING, "是否確認強制關閉", "請輸入確認"))
				.complete();
	}

	public boolean checkMaster(SlashCommandInteractionEvent event) {
		String[] masaterIds = { "431253739391942656", "552083728147415056", "1066552829642276975" };
		for (String masterId : masaterIds) {
			if (event.getUser().getId().equals(masterId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		// make sure we handle the right command
		switch (event.getName()) {
		case "你還活著嗎":
			long time = System.currentTimeMillis();
			event.reply("還活著，目前機器人的延遲為 : ").setEphemeral(true) // reply or acknowledge
					.flatMap(v -> event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then
																														// edit
																														// original
					).queue(); // Queue both reply and edit
			break;
		case "取得記憶體用量":
			event.reply("⌛ 取得中記憶體資訊中...").queue();
			event.getHook().editOriginal(HardwareInfo.getMem()).queue();
			break;
		case "開啟伺服器":
			if (!checkMaster(event)) {
				event.reply("✋ 我不認識你").queue();
				break;
			}
			event.reply("⌛ 伺服器啟動中...").queue();
			boolean startServer = ServerController.startServer();
			if (startServer) {
				event.getHook().editOriginal("✅ 伺服器啟動成功，請幾分鐘後再進入遊戲").queue();
				;
			} else {
				event.getHook().editOriginal("❌ 伺服器啟動失敗").queue();
			}

			break;
		case "關閉伺服器":
			if (!checkMaster(event)) {
				event.reply("✋ 我收到指令了，但是我不想動").queue();
				break;
			}
			event.reply("⌛ 伺服器關閉中...").queue();
			ServerController.shutdownServer();
			event.getHook().editOriginal("✅ 伺服器已關閉").queue();
			break;
		case "備份伺服器":
			if (!checkMaster(event)) {
				event.reply("✋ 有變態!!!").queue();
				break;
			}
			event.reply("⌛ 執行備份中...").queue();
			ServerBackup serverBackup = new ServerBackup();
			serverBackup.start(event);
			break;
		case "發送訊息到伺服器":
			if (!checkMaster(event)) {
				event.reply("✋ 你是誰?").queue();
				break;
			}
			event.reply("⌛ 發送訊息中...").queue();
			try {
				ServerController.sendMsgToServer(event, event.getOption("訊息", OptionMapping::getAsString));
			} catch (IOException e) {
				event.getHook().editOriginal("❌ 訊息發送失敗").queue();
			}
			break;
		case "發送指令到伺服器":
			if (!checkMaster(event)) {
				event.reply("✋ 我要報警了喔").queue();
				break;
			}
			event.reply("⌛ 發送指令中...").queue();
			try {
				ServerController.sendCommandToServer(event, event.getOption("指令", OptionMapping::getAsString));
			} catch (IOException e) {
				event.getHook().editOriginal("❌ 指令發送失敗").queue();
			}
			break;
		case "強制關閉伺服器線程":
			if (!checkMaster(event)) {
				event.reply("✋ 就說很危險了喔").queue();
				break;
			}

			if (event.getOption("是否確認強制關閉", OptionMapping::getAsString).equals("確認")) {
				event.reply("⌛ 強制關閉中...").queue();
				try {
					ServerController.killServer();
					event.getHook().editOriginal("✅ 已強制關閉").queue();
					break;
				} catch (InterruptedException e) {
					event.getHook().editOriginal("❌ 指令發送失敗").queue();
					break;
				}

			}
			event.getHook().editOriginal("❌ 無法確認你的意圖").queue();
			break;
		default:
			System.out.printf("Unknown command %s used by %#s%n", event.getName(), event.getUser());
		}

	}

}
