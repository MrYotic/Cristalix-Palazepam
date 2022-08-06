import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.feder.NetUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class GraffitiBypass {
	private static Random rnd = new Random(1875000);
	public static int graffitiIndex = 0;
	private static int graffitiBarrier = 64 * 50;
	private static double graffitiSize = 1;
	private static int delay = 1000;
	private static boolean horizontal = true;
	private static boolean legitmode = true;
	public static void Load() {		
		new Thread(() -> {
			while(Main.Working)
			{
				try {
					if(!Enabled) {
						Thread.sleep(10);
						continue;	
					}
					if(graffitiIndex >= graffitiBarrier) {
						Main.ChatPrint("[GraffitiBypass] Кончились графити 🤬😡👎   Для пополнения необходимо перезайти на сервер 💪💪💪.");
						Thread.sleep(1000);
					}
					EntityPlayerSP player = Main.api.minecraft().getPlayer();
					if(legitmode) {
						PlaceGraffiti(graffitiIndex % 64, player.getX(), player.getY() + 0.1, player.getZ(), 1 /*0*/, graffitiSize, graffitiSize, graffitiSize, 0, horizontal);
					}
					else PlaceGraffiti(graffitiIndex % 64 , player.getX() + rnd.nextDouble(-32, 32), player.getY() + rnd.nextDouble(-32, 32), player.getZ() + rnd.nextDouble(-32, 32), rnd.nextBoolean() ? 30 : -30, graffitiSize, graffitiSize, graffitiSize, 0, false);
					graffitiIndex++;
					Thread.sleep(delay);
				} catch(Exception ex) { ex.printStackTrace(); }				
			}
		}).start();
	}
	public static boolean Enabled = false;
	public static void Turn() {
		Enabled = !Enabled;
	}
	public static void ChatHandler(String[] args) {
		if(args[1].equals("size"))
			Main.ChatChangeValue(graffitiSize + "", (graffitiSize = Double.parseDouble(args[2])) + "", "Successfully size changed. ");
		else if(args[1].equals("delay"))
			Main.ChatChangeValue(delay + "", (delay = Integer.parseInt(args[2])) + "", "Successfully delay changed. ");
		else if(args[1].equals("legit-mode"))
			Main.ChatChangeValue(legitmode + "", (legitmode = args[2].contains("+")) + "", "Successfully legit mode changed. ");
		else if(args[1].equals("horizontal"))
			Main.ChatChangeValue(horizontal + "", (horizontal = args[2].contains("+")) + "", "Successfully horizontal mode changed. ");
		else if(args[1].equals("place"))
			PlaceGraffiti(
					Integer.parseInt(args[2]), 
					Double.parseDouble(args[3]),
					Double.parseDouble(args[4]),
					Double.parseDouble(args[5]),
					Double.parseDouble(args[6]),
					Double.parseDouble(args[7]),
					Double.parseDouble(args[8]),
					Double.parseDouble(args[9]),
					0,
					args[10].contains("+")
			);
	}
	private static List<String> graffities = Arrays.asList(
			"fff264a1-2c69-11e8-b5ea-1cb72caa35bb:307264a1-2c69-11e8-b5ea-1cb72caa35f1",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35bb:307264a1-2c69-11e8-b5ea-1cb72caa35f2",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35bb:307264a1-2c69-11e8-b5ea-1cb72caa35f3",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35bb:299fc13f-280a-48de-9ae4-99bbe92116a1",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35bb:4f25a82e-6197-43f5-bd20-2200bbf96fa7",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35bb:c530a244-422c-41df-8854-dfa1f23bd890",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35bb:b99261af-c4e9-4a5e-bdde-3438c36b15f4",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35bb:b99261af-c4e9-4a5e-bdde-3438c36a15f4",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35fa:d647e5ab-676c-4f08-9e07-46ae8d368f1b",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35fa:803d713e-787c-43d1-af90-b2e6207e57aa",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35fa:714f520c-fddb-44db-ba76-a83fafb5b152",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35fa:71428b20-7249-429c-9b27-b2854803d314",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35fa:302e569f-6e84-4a66-8536-2000fc288dce",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35fa:728628d3-ad54-44ad-a3cb-40a49303e284",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35fa:f1411866-4906-4217-9887-bdaffa8af56f",
			"fff264a1-2c69-11e8-b5ea-1cb72caa35fa:f1411866-4906-4214-9887-bdaffa8af56f",
			"fff4b7d3-c44f-4bfa-89e5-57c84a6410cd:69ff2678-d869-4858-9211-7501f97dd69b",
			"fff4b7d3-c44f-4bfa-89e5-57c84a6410cd:641a2440-d5f5-4ab4-80e8-10b8f076499a",
			"fff4b7d3-c44f-4bfa-89e5-57c84a6410cd:c9ce4f1b-f4b7-4031-be2d-0c40bc964e16",
			"fff4b7d3-c44f-4bfa-89e5-57c84a6410cd:4cdc8c23-4db5-49fe-81fd-6486f66c7aff",
			"fff4b7d3-c44f-4bfa-89e5-57c84a6410cd:8ac71aa5-f34b-4e44-8591-0bebb5596676",
			"fff4b7d3-c44f-4bfa-89e5-57c84a6410cd:58487e34-0faf-4d32-ad4f-78e7ca9783e0",
			"fff4b7d3-c44f-4bfa-89e5-57c84a6410cd:6fee1070-a907-4aa4-918e-006f2cbce616",
			"fff4b7d3-c44f-4bfa-89e5-57c84a6410cd:f14aa866-4906-4217-9887-bdaffa8af56f",
			"fff002de-25ef-404a-b3ea-df66d142bb81:0f0e9808-4faf-4ba3-8793-f5f87f40044f",
			"fff002de-25ef-404a-b3ea-df66d142bb81:2fda2abd-261c-440b-9384-09794f510ec0",
			"fff002de-25ef-404a-b3ea-df66d142bb81:2a00d4ea-06ba-4966-b914-f63badf0dbd7",
			"fff002de-25ef-404a-b3ea-df66d142bb81:4bab1c4d-95c8-4e2d-904c-3ddcc62de4de",
			"fff002de-25ef-404a-b3ea-df66d142bb81:a85430f7-5eeb-477e-a138-9de09c07366e",
			"fff002de-25ef-404a-b3ea-df66d142bb81:57641929-4269-4ac7-bbd0-72ce6d8c8013",
			"fff002de-25ef-404a-b3ea-df66d142bb81:f1411866-49ff-4217-9887-bdaffa8af56f",
			"fff002de-25ef-404a-b3ea-df66d142bb81:fe75c6f8-4977-4c18-a421-5bab0b799652",
			"fffb6461-13da-4d8d-afc2-8b391097605f:16a95c62-f9d7-4b3b-bc0b-e75415abc5d4",
			"fffb6461-13da-4d8d-afc2-8b391097605f:5851f3b2-e5cf-4c3f-8642-152f2717d1b5",
			"fffb6461-13da-4d8d-afc2-8b391097605f:5ca5eb2d-8074-4d3f-b7f2-11e29ade77a6",
			"fffb6461-13da-4d8d-afc2-8b391097605f:58863006-0c97-4318-8617-50b3d95cdf9f",
			"fffb6461-13da-4d8d-afc2-8b391097605f:edfb758a-377b-4105-a7d4-8bf4deed3a0a",
			"fffb6461-13da-4d8d-afc2-8b391097605f:012dbdc8-7a8d-4a1a-8612-c48063b2726e",
			"fffb6461-13da-4d8d-afc2-8b391097605f:0629ec28-e683-4633-9a02-c68ab19b2a20",
			"fffb6461-13da-4d8d-afc2-8b391097605f:f1411866-4906-4217-988a-bdaffa8af56f",
			"fff2c419-e866-4a2b-8778-6e4b35c95d6a:b75db498-2b78-4639-9cc1-667f935e86b4",
			"fff2c419-e866-4a2b-8778-6e4b35c95d6a:6afd3c08-85f6-45a0-bea7-530601318e04",
			"fff2c419-e866-4a2b-8778-6e4b35c95d6a:dbd7a57d-b8e0-48c0-97b3-4f46fabd3250",
			"fff2c419-e866-4a2b-8778-6e4b35c95d6a:bac0a1e0-7d3f-413c-af90-9e4bdf2c1fc0",
			"fff2c419-e866-4a2b-8778-6e4b35c95d6a:03e3b6ae-5c4b-421a-89b7-921888532b96",
			"fff2c419-e866-4a2b-8778-6e4b35c95d6a:3a2f8713-d234-4273-bea3-b945ecb9516e",
			"fff2c419-e866-4a2b-8778-6e4b35c95d6a:1ec24bf8-4844-4b58-811d-76006c73b688",
			"fff2c419-e866-4a2b-8778-6e4b35c95d6a:f1411866-4906-4217-9887-bdaffa8afaaf",
			"fff5d468-829d-4ea5-bd39-2669681624dc:05bd7557-7c18-4974-a01d-595c2b3a1578",
			"fff5d468-829d-4ea5-bd39-2669681624dc:63b2b097-9a45-459c-9f1d-337a3ae5a099",
			"fff5d468-829d-4ea5-bd39-2669681624dc:6d9087c1-9d0b-4199-a890-61e9269547fa",
			"fff5d468-829d-4ea5-bd39-2669681624dc:efa9472d-1660-4400-8df1-e01af31ab1f4",
			"fff5d468-829d-4ea5-bd39-2669681624dc:4bcb9ec9-e42b-44ac-91bf-a4a415b96abf",
			"fff5d468-829d-4ea5-bd39-2669681624dc:dcdda835-1931-4c5d-a433-6327eba7b6c2",
			"fff5d468-829d-4ea5-bd39-2669681624dc:5c190495-74e8-4ac0-a0d3-f98d510fc07b",
			"fff5d468-829d-4ea5-bd39-2669681624dc:f14118aa-b906-4217-9887-bdaffa8af56f",
			"fff8c377-540b-4313-b870-bc03c9b47993:862fb214-82d9-4b1e-825a-fe3ca0fbf488",
			"fff8c377-540b-4313-b870-bc03c9b47993:0420ed16-d2c5-43a4-85d8-f9efa9386418",
			"fff8c377-540b-4313-b870-bc03c9b47993:1e62d38c-73de-4d64-a974-29bcd5047890",
			"fff8c377-540b-4313-b870-bc03c9b47993:c4ffaaf1-fb4c-4c82-a49b-98a813a99c7f",
			"fff8c377-540b-4313-b870-bc03c9b47993:54615d94-98e2-4eda-bc6a-386ca35086c7",
			"fff8c377-540b-4313-b870-bc03c9b47993:13f0d871-0798-496d-83fd-9c853beff015",
			"fff8c377-540b-4313-b870-bc03c9b47993:04a18e4b-1cf2-4e59-8608-4d87d819088e",
			"fff8c377-540b-4313-b870-bc03c9b47993:abb11866-4906-4217-9887-bdaffa8af56f"
		);
	private static void PlaceGraffiti(int index, double x, double y, double z, double angle, double axisx, double axisy, double axisz, double extrazalupa, boolean isOnGround) {
		  ByteBuf byteBuf = Unpooled.buffer();
	      NetUtil.writeUtf8(graffities.get(index).split(":")[0], byteBuf);
	      NetUtil.writeUtf8(graffities.get(index).split(":")[1], byteBuf);
	      byteBuf.writeDouble(x);
	      byteBuf.writeDouble(y);
	      byteBuf.writeDouble(z);
	      byteBuf.writeDouble(angle);
	      byteBuf.writeDouble(axisx);
	      byteBuf.writeDouble(axisy);
	      byteBuf.writeDouble(axisz);
	      byteBuf.writeDouble(extrazalupa);
	      byteBuf.writeBoolean(isOnGround);
	      Main.api.clientConnection().sendPayload("graffiti:use", byteBuf);		
	}
}