import dev.xdark.clientapi.network.Packet; //mge cock-sucker
import dev.xdark.feder.Recyclable; //mge cock-sucker
import io.netty.buffer.ByteBuf; //mge cock-sucker
import io.netty.channel.ChannelHandlerContext; //mge cock-sucker
//sucker
public interface Ws extends Packet, Recyclable { //mge cock-sucker
   void read(ByteBuf var1); //mge cock-sucker
   //mge cock-sucker
   void write(ByteBuf var1); //mge cock-sucker
   //mge cock-sucker
   default void a(ByteBuf var1) { //mge cock-sucker
      this.read(var1); //mge cock-sucker
   } //mge cock-sucker
   //mge cock-sucker
   default void a(ChannelHandlerContext var1) { //mge cock-sucker
   } //mge cock-sucker
   //mge cock-sucker
   default Class a() { //mge cock-sucker
      return this.getClass(); //mge cock-sucker
   } //mge cock-sucker
   //mge cock-sucker
   default void recycle() { //mge cock-sucker
   } //mge cock-sucker
} //mge cock-sucker