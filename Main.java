import org.lwjgl.input.Keyboard;
import dev.xdark.clientapi.ClientApi;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.event.Listener;
import dev.xdark.clientapi.event.chat.ChatReceive;
import dev.xdark.clientapi.event.chat.ChatSend;
import dev.xdark.clientapi.event.input.KeyPress;
import dev.xdark.clientapi.event.network.ServerConnect;
import dev.xdark.clientapi.event.network.ServerQuit;
import dev.xdark.clientapi.event.network.ServerSwitch;
import dev.xdark.clientapi.event.render.GuiOverlayRender;
import dev.xdark.clientapi.math.Vec3d;
import dev.xdark.clientapi.text.Style;
import dev.xdark.clientapi.text.Text;
import dev.xdark.clientapi.text.TextFormatting;

public class Main implements dev.xdark.clientapi.entry.ModMain, Listener {
	public static ClientApi api;
	public static boolean Working = true;
	private static int t = 1176832, f = 16058368;
	public void load(ClientApi api) {
		this.api = api;
		KeyPress.BUS.register(this, e -> {
			switch(e.getKey()) {
				case Keyboard.KEY_F6: GraffitiBypass.Turn(); break;
				case Keyboard.KEY_F7: Spammer.Turn(); break;
				case Keyboard.KEY_F8: HitCrasher.Turn(); break;
			}
		}, Integer.MAX_VALUE);
		ChatSend.BUS.register(this, e ->
		{
			try {
				String text = e.getMessage();
				if(text.startsWith("-")) {
					e.setCancelled(true);
					String[] args = text.toLowerCase().split(" ");
					if(args[0].equals("-graffiti"))
						GraffitiBypass.ChatHandler(args);
					else if(args[0].equals("-spammer"))
						Spammer.ChatHadnler(args, text);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
				api.chat().printChatMessage(Text.of(ex.getMessage()));
			}
		}, Integer.MAX_VALUE);
		GuiOverlayRender.BUS.register(this, e -> {
			int w = api.resolution().getScaledWidth();
			int h = api.resolution().getScaledHeight();
			String spammer = "Spammer [F7]";
			int spammerWidth = api.galacticFontRenderer().getStringWidth(spammer);
			String hitCrasher = "HitCrasher [F8]";
			int hitCrasherWidth = api.galacticFontRenderer().getStringWidth(hitCrasher);
			String graffitiBypass = "GraffitiBypass [F6]";
			int graffitiBypassWidth = api.galacticFontRenderer().getStringWidth(graffitiBypass);
			api.fontRenderer().drawStringWithShadow(graffitiBypass, w - graffitiBypassWidth - 2, h - 32, GraffitiBypass.Enabled ? t : f);
			api.fontRenderer().drawStringWithShadow(hitCrasher, w - hitCrasherWidth - 2, h - 42, HitCrasher.Enabled ? t : f);
			api.fontRenderer().drawStringWithShadow(spammer, w - spammerWidth - 2, h - 52, Spammer.Enabled ? t : f);
		}, Integer.MAX_VALUE);
		ChatReceive.BUS.register(this, e -> Spammer.ChatReceive(e), Integer.MAX_VALUE);
		ServerSwitch.BUS.register(this, e -> ServerHandle(), Integer.MAX_VALUE);
		ServerQuit.BUS.register(this, e -> ServerHandle(), Integer.MAX_VALUE);
		ServerConnect.BUS.register(this, e -> ServerHandle(), Integer.MAX_VALUE);
		GraffitiBypass.Load();
		HitCrasher.Load();
		Spammer.Load();
	}
    public void unload()
    { 
    	KeyPress.BUS.unregisterAll(this);
    	ChatSend.BUS.unregisterAll(this);
    	GuiOverlayRender.BUS.unregisterAll(this);
    	ServerSwitch.BUS.unregisterAll(this);
    	ServerQuit.BUS.unregisterAll(this);
		ServerConnect.BUS.unregisterAll(this);
		Working = false;
    }	  
	private static void ServerHandle() {
		if(HitCrasher.Enabled)
			HitCrasher.Turn();
		if(GraffitiBypass.Enabled)
			GraffitiBypass.Turn();
		GraffitiBypass.graffitiIndex = 0;		
	}
    public static double Entitydistance(Entity own, Entity dom) {
		return Math.abs(Vec3d.of(own.getX(), own.getY(), own.getZ()).distanceTo(dom.getX(), dom.getY(), dom.getZ()));
	}
	public static void ChatPrint(String message) {
		Text msg = Text.of(message);
		Style style = Style.create();
		style.setColor(TextFormatting.GRAY);
		api.chat().printChatMessage(msg);
	}
	public static void ChatChangeValue(String old, String cur, String message) {
		Text msg = Text.of(message + " ");
		{
			Style style = Style.create();
			style.setBold(false);
			style.setColor(TextFormatting.GRAY);
			msg.setStyle(style);
		}
		Text oldValue = Text.of(old);
		{
			Style style = Style.create();
			style.setBold(true);
			style.setColor(TextFormatting.RED);
			oldValue.setStyle(style);
		}
		Text split = Text.of(" => ");
		{
			Style style = Style.create();
			style.setBold(false);
			style.setColor(TextFormatting.GRAY);
			split.setStyle(style);
		}
		Text newValue = Text.of(cur);
		{
			Style style = Style.create();
			style.setBold(true);
			style.setColor(TextFormatting.GREEN);
			newValue.setStyle(style);
		}
		msg.append(oldValue);
		msg.append(split);
		msg.append(newValue);
		api.chat().printChatMessage(msg);
	}
}