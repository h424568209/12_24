import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ParseURL {
    public static void main(String[] args) throws UnsupportedEncodingException {
        int index;
        String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_i" + "dx=1&tn=baidu&wd=c%2B%2B&rsv_pq=a4fc69f200076536&rsv_t" + "=2b96NbhzR2Ir%2BdW1z0tx0yYZJG0xZgadx7dwH0Qkvn6IH793SOANRIZN" + "aj0&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_sug3=3&rsv_sug1=3&rsv" + "_sug7=10" + "0&rsv_sug2=0&prefixsug=c%252B%252B&rsp=2&" + "inputT=1160&rsv_sug4=1161";
        index = url.indexOf("://");
        String schema = url.substring(0, index);
        url = url.substring(index + 3);
        System.out.println("协议方案名 ："+schema);

        index = url.indexOf("/");
        String hostAndPort = url.substring(0, index);
        System.out.println("主机加端口号 ："+hostAndPort);

        url = url.substring(index);
        String host;
        int port;
        if (hostAndPort.contains(":")) {
            String[] group = hostAndPort.split(":");
            host = group[0];
            port = Integer.valueOf(group[1]);
        } else {
            host = hostAndPort;
            port = knowPorts.get(schema);
        }
        System.out.println("主机："+host);
        System.out.println("端口号：" +port);

        index = url.indexOf("?");
        String path = url.substring(0,index);
        System.out.println("路径 ："+path);
        url = url.substring(index+1);

        String queryString ;
        String segment = "";
        index=  url.indexOf("#");
        if(index != -1){
            queryString = url.substring(0,index);
            segment = url.substring(index+1);
        }else{
            queryString = url;
        }
        String[]kv = queryString.split("&");
        for(String k :kv){
            //解码成UTF-8
            System.out.println(URLDecoder.decode(k,"UTF-8"));
        }
        System.out.println("片段标识符："+segment);

    }
    private static Map<String,Integer> knowPorts = new HashMap<>();
    static {
        knowPorts.put("http",80);
        knowPorts.put("https",443);
        knowPorts.put("jdbc:mysql",3306);
    }
}

