package nio.sendFile;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用Nio发送文件接收方
 */
@Slf4j
public class NioSendFileReceiver {

    static class Client {

    }

    private static HashMap<SocketChannel, Client> clientHashMap = new HashMap<SocketChannel, Client>();

    public static void main(String[] args) throws IOException {
        receiver();
    }

    public static void receiver() throws IOException {
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
                    clientHashMap.put(socketChannel, new Client());
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {


                }
            }
        }

    }
}
