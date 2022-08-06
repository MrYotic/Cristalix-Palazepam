import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityLivingBase;
import dev.xdark.clientapi.entity.EntityPlayer;
import dev.xdark.clientapi.math.Vec3d;

public class RootReflection {
	public static void SilentHitEntity(EntityLivingBase entity) {
		Main.api.clientConnection().sendPacket(new XR((NN)entity));
	}
    public static Object GetField(Object obj, Class<?> claz, String fieldName, String returnType) {
	    try {
		   for(Field field : claz.getFields())
			   if(field.getName().equals(fieldName))
				   if(field.getType().getName().equals(returnType))
				   {
					   field.setAccessible(true);
					   return field.get(obj);
				   }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
    }
    public static Set<NN> GetEntities() {
    	return (Set<NN>)GetField((aej)Main.api.minecraft().getWorld(), aej.class, "b", "java.util.Set");
    }    
}