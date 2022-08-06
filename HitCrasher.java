import java.util.List;
import java.util.Set;

import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityLivingBase;
import dev.xdark.clientapi.text.Text;

public class HitCrasher {
	private static int iterations = Math.abs("C15H10Cl2N2O2".hashCode() | 0xFFFF);
	public static void Load() {
		new Thread(() -> {
			while(Main.Working)
			{
				try {
					if(!Enabled) {
						Thread.sleep(10);
						continue;
					}
					Set<NN> entities = RootReflection.GetEntities();
					if(entities == null) continue;
					for(NN entity : entities)
						if(entity != null)
							if(Main.Entitydistance(Main.api.minecraft().getPlayer(), entity) < 8)
								if(entity instanceof EntityLivingBase && !((EntityLivingBase)entity).getName().equals(Main.api.minecraft().getPlayer().getName()))
									for(int i = 0; i < iterations; i++)
										if(Enabled)
											RootReflection.SilentHitEntity((Rn)entity);
				} catch(Exception ex) { ex.printStackTrace(); }
			}
		}).start();
	}
    public static boolean Enabled = false;
	public static void Turn() {;
		Enabled = !Enabled;
	}
}