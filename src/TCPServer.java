import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    private static class ServiceTask implements Runnable{
        private final Socket socket;
        private ServiceTask(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                Reader reader = new InputStreamReader(is,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);

                Writer writer = new OutputStreamWriter(os,"UTF-8");
                PrintWriter printWriter = new PrintWriter(writer,false);

                String message;
                while((message = bufferedReader.readLine())!=null){
                    System.out.println("我收到了一条消息   "+ message);
                    printWriter.println(message);
                    printWriter.flush();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8888);
        while(true){
            Socket socket = serverSocket.accept();
            pool.execute(new ServiceTask(socket));
        }
    }
}
