import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SimpleHttpClient {
    public static void main(String[] args) throws IOException {
        String request = "GET / HTTP/1.0\r\nHost: www.baidu.com.vip\r\n\r\n";
        Socket socket = new Socket("www.baidu.com",80);
        socket.getOutputStream().write(request.getBytes("UTF-8"));
        byte[]bytes = new byte[4096];
        int 第一次读到的数据长度 = socket.getInputStream().read(bytes);
        int index = -1;
        for(int i = 0 ; i <第一次读到的数据长度 -3 ; i++){
            if(bytes[i] == '\r'&& bytes[i+1] =='\n' && bytes[i+2] == '\r'&&bytes[i+3]=='\n')
            {   index = i;
            break;
        }
        }
        ByteArrayInputStream byteArrayInputStream =  new ByteArrayInputStream(bytes,0,index+4);
        Scanner scanner = new Scanner(byteArrayInputStream,"UTF-8");
        String statuLine = scanner.nextLine();
        System.out.println("状态行"+statuLine);
        String[]group = statuLine.split(" ");
        System.out.println("响应版本" +group[0].trim());
        System.out.println("响应状态码" +group[1].trim());
        System.out.println("响应状态描述" +group[2].trim());

        String line ;
        int 正文长度 = -1;
        while(!(line = scanner.nextLine()).isEmpty()){
            String []kv = line.split(":");
            String k = kv[0].trim();
            String v = kv[1].trim();
            System.out.println("响应头: "+ k +"="+v);
            if(k.equalsIgnoreCase("Content-Length")){
                正文长度 = Integer.valueOf(v);
            }
        }
        System.out.println("zhenwenchangdu==" + 正文长度);
        System.out.println("index == " +index);
    }
}
