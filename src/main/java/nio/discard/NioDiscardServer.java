package nio.discard;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * java 使用Nio实现简易聊天室服务端
 */
@Slf4j
public class NioDiscardServer {

    public static void main(String[] args) throws IOException {
        startServer();
    }

    public static void startServer() throws IOException {
        Selector selector = Selector.open();

        //创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(18899));
        serverSocketChannel.configureBlocking(false);
        //注册链接事件到选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        log.info("开启监听！");
        while (selector.select() > 0) {

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                log.info("等待事件中");

                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {

                    //连接事件。将可读时间注册到选择器
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    log.info("监听到链接：{}", socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel readChannel = (SocketChannel) selectionKey.channel();
                    int length;
                    while ((length = readChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        log.info(readChannel.getRemoteAddress() + ":" + new String(byteBuffer.array(), 0, length));
                        byteBuffer.clear();
                    }
                }
                iterator.remove();
            }
        }


    }

}
