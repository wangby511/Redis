import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class simulateRedisSocket {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(6379);
            Socket rec = server.accept();
            byte [] result = new byte[2048];
            rec.getInputStream().read(result);
            System.out.println(result);

            //https://www.cnblogs.com/wangby511/p/12303714.html
            //Jedis传给Redis采用RESP协议

        } catch (IOException e) {
            e.printStackTrace();
        }

        //wangby511deMacBook-Pro:~ wangboyuan$ lsof -i:6379
//        COMMAND     PID       USER   FD   TYPE            DEVICE SIZE/OFF NODE NAME
//        redis-ser 90644 wangboyuan    6u  IPv6 0xe25655ee8e7595b      0t0  TCP *:6379 (LISTEN)
//        redis-ser 90644 wangboyuan    7u  IPv4 0xe25655f2072e60b      0t0  TCP *:6379 (LISTEN)
        //本来想用此监听6379端口 没想到因为冲突这个无法运行起来

    }
}
