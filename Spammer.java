import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import dev.xdark.clientapi.event.chat.ChatReceive;
import dev.xdark.clientapi.network.NetworkPlayerInfo;

public class Spammer {
	public static void Load() {
		new Thread(() -> {
			while(Main.Working)
			{
				try {
					if(!Enabled) {
						Thread.sleep(10);
						continue;					
					}
		 		    for(NetworkPlayerInfo NPI : Main.api.clientConnection().getPlayerInfos()) {
		 		    	try {
		 		    		String name = NPI.getGameProfile().getName();
		 		    		if(!users.contains(name) && !name.equals(Main.api.minecraft().getPlayer().getGameProfile().getName()))
		 		    			users.add(name);
		 		    	} catch(Exception ex) { ex.printStackTrace(); }
		 		    }
		 		    
		 		    String user = users.get(index % users.size());		 		    
				    Main.api.chat().sendChatMessage("/friend add " + user);
				    Main.api.chat().sendChatMessage("/m " + user + " " + spammMessage);
				    for(int i = 0; i < 50; i += 1) 
				    {
				    	Main.api.chat().sendChatMessage("/r " + spammMessage);
				    	Thread.sleep(50);
				    }
				    Main.api.chat().sendChatMessage("/p i " + user);
				    Main.api.chat().sendChatMessage("/friend remove " + user);
				    index++;				    
				    if(partyUsersCount > 0 && partyUsersCount <= 5)
				    	for(int i = 0; i < 100; i++)
				    	{
				    		Main.api.chat().sendChatMessage("/pc " + spammMessage);
				    		Thread.sleep(1);
				    	}
				    if(partyUsersCount == 5) {
				    	Main.api.chat().sendChatMessage("/p disband");
				    	Main.api.chat().sendChatMessage("/p create");
				    	partyUsersCount = 0;	
				    }			    	
				} catch(Exception ex) { ex.printStackTrace(); }				
			}
		}).start();
	}
	public static boolean Enabled = false;
	public static void Turn() {
		Enabled = !Enabled;
	}
	private static boolean hide = true;
	private static List<String> users = new ArrayList<String>();
	private static String spammMessage = "Тебя ждёт настя в первом руме - https://discord.gg/xKvWetGJ";
	private static int partyUsersCount = 0;
	private static int index = 0;
	public static void ChatReceive(ChatReceive e) {
		String text = e.getText().getUnformattedText();
		if(text.contains("У этого игрока выключены личные сообщения") || text.contains("В данный момент вы не можете писать игроку")) { }
		else if(text.contains("Подожди немного перед отправкой следующего сообщения")) { }
		else if(text.contains("ЛС [Я » ")){ }
		else if(text.contains("[PC]")) { }
		else if(text.contains("на тусовку")) { }
		else if(text.contains("теперь друзья.")) { }
		else if(text.contains("Вы отозвали заявку в друзья.")) { }
		else if(text.contains("хочет добавить вас в друзья.")) { }
		else if(text.contains("Нажмите, чтобы принять!")) { }
		else if(text.contains("Тусовка расформирована")) 
			partyUsersCount = 0;
		else if(text.contains("принял приглашение на тусовку")) 
			partyUsersCount += 1;
		else if(text.contains("оффлайн.")) {
			List<String> words = Arrays.asList(text.split(" "));
			users.remove(words.get(words.indexOf("Игрок") + 1));
		} 
		else if(text.contains("Вы уже отправили запрос этому игроку") || text.contains("Запрос в друзья отправлен игроку")) { }
		else return;
		e.setCancelled(hide);
	}
    public static void ChatHadnler(String[] args, String text) {
    	if(args[1].equals("message"))
    		Main.ChatChangeValue(spammMessage + "", (spammMessage = text.substring(16)) + "", "Successfully spamm message changed. ");
    	else if(args[1].equals("hide"))
    		Main.ChatChangeValue(hide + "", (hide = args[2].contains("+")) + "", "Successfully message hide changed. ");
    }
}