import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IODemo {
    /**
     * 1、可以从文件中读
     * 2、可以从网络中读（网卡/TCP连接）
     * 3、可以从内存中读（内存中的一段空间当成输入源）
     * 4、可以从标准输入读
     */
    private static InputStream 获取一个输入流() throws FileNotFoundException {
        InputStream inputStream;
        inputStream = new FileInputStream("本地文件.txt");
        return inputStream;
    }
    /**
     * 1、直接通过字节的方式读，然后程序进行字符编码（buffer大小 < 数据长度/精确控制字符  都比较麻烦）
     * 2、把Stream转为Reader，进行字符形式读取
     *  2.1、 直接读
     *  2.2、 BufferedReader    readLine
     * 3、Scanner
     */
    private static String 从字节流中最终获得字符数据(InputStream is) throws IOException {
        /**
         * 使用buffer
         */
        /*
        第一种
        byte[]buffer = new byte[1024];
        int len = is.read(buffer);
        String message = new String(buffer,0,len,"UTF-8");
        return message;
        */

        //第二种
        /**
         * 将buffer转为Reader
         */
        Reader reader = new InputStreamReader(is,"UTF-8");
        char[]buffer= new char[10];
        int len = reader.read(buffer);
        String message = new String(buffer,0,len);
//        return message;

        /**
         * 使用StringBuffer
         */
//        StringBuffer sb = new StringBuffer();
//        Reader reader = new InputStreamReader(is,"UTF-8");
//        char[]buffer = new char[10];
//        int len;
//        while((len = reader.read(buffer))!=-1){
//            sb.append(buffer,0,len);
//        }
//        return sb.toString();
//

        /**
         * 使用readLine进行
         */
//        Reader reader = new InputStreamReader(is,"UTF-8");
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        String line;
//        StringBuffer sb = new StringBuffer();
//        while((line = bufferedReader.readLine()) !=null){
//            sb.append(line+"\r\n");
//        }
//        return sb.toString();

        /**
         * 使用Scanner
         */
        Scanner scanner = new Scanner(is,"UTF-8");
        return scanner.nextLine();
    }
    private static OutputStream 获取一个输出流() throws IOException {
        OutputStream os = new FileOutputStream("本地输出文件2.txt");

        //Socket socket = new Socket("www.baidu.com",80);
        //OutputStream os = socket.getOutputStream();
        // OutputStream os = new ByteArrayOutputStream();
        return os;
    }
    private static void 输出一段字符(OutputStream os,String message) throws IOException {

        //byte[] buffer = message.getBytes("UTF-8");
        //os.write(buffer);

        Writer writer = new OutputStreamWriter(os,"UTF-8");
        writer.append(message);
        writer.flush();

        PrintWriter printWriter = new PrintWriter(writer,false);
        printWriter.printf("%s",message);
        printWriter.flush();
    }

    public static void main(String[] args) throws IOException {
        InputStream is = 获取一个输入流();
        String message = 从字节流中最终获得字符数据(is);
        System.out.println(message);
        OutputStream os = 获取一个输出流();
        输出一段字符(os,"你是谁a ，你在哪\r\n");
    }
}
