package nio.discard;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

@Slf4j
/**
 * java 使用Nio实现简易聊天室客户端
 */
public class NioDiscardClient {
    public static void main(String[] args) throws IOException {
        startClient();
    }

    public static void startClient() throws IOException {


        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 18899));

        channel.configureBlocking(false);

        while (!channel.finishConnect()) {
            log.info("连接中......");
        }
        log.info("连接成功");
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner in = new Scanner(System.in);
        log.info("请输入要发送的文本");
        while (in.hasNext()) {
            String next = in.next();
            byteBuffer.put(next.getBytes());
            byteBuffer.flip();
            channel.write(byteBuffer);

            byteBuffer.clear();
        }


    }

}

