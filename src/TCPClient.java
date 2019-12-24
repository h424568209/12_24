import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.43.166",8888);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        Reader reader = new InputStreamReader(is,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);

        Writer writer = new OutputStreamWriter(os,"UTF-8");
        PrintWriter printWriter = new PrintWriter(writer,false);

        String []message = {"2","3","4","5"};
        for(String m : message){
            printWriter.println(m);
            printWriter.flush();

            String echomessage = bufferedReader.readLine();
            System.out.println("对方回复了:" + echomessage);
        }
        socket.close();
    }
}
