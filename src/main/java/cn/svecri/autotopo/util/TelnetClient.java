package cn.svecri.autotopo.util;

import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@ToString
public class TelnetClient {
    //结束标识字符串,Windows中是>,Linux中是#
    private String prompt = "#";
    //结束标识字符
    private char promptChar = '>';
    private final org.apache.commons.net.telnet.TelnetClient telnet;
    // 输入流,接收返回信息
    private InputStream in;
    // 向服务器写入 命令
    private PrintStream out;
    private String deviceName;

    /**
     * @param termtype	协议类型：VT100、VT52、VT220、VTNT、ANSI
     * @param prompt	结果结束标识
     */
    public TelnetClient(String termtype, String prompt,String name){
        telnet = new org.apache.commons.net.telnet.TelnetClient(termtype);
        setPrompt(prompt);
        deviceName=name;
    }

    public TelnetClient(String termtype, String prompt){
        telnet = new org.apache.commons.net.telnet.TelnetClient(termtype);
        setPrompt(prompt);
    }

    public TelnetClient(){
        telnet = new org.apache.commons.net.telnet.TelnetClient();
    }
    /**
     * 登录到目标主机
     */
    public Boolean login(String ip, int port,String password){
        try {
            telnet.connect(ip, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            readUntil("Password:");
            write(password);
            readUntil(">");
            write("enable");
            readUntil("Password:");
            write(password);
            return readUntil("#") != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 读取分析结果
     *
     * @param pattern	匹配到该字符串时返回结果
     * @return read result
     */
    public String readUntil(String pattern) {
        StringBuffer sb = new StringBuffer();
        try {
            char lastChar = (char)-1;
            boolean flag = pattern!=null&&pattern.length()>0;
            if(flag) {
                lastChar = pattern.charAt(pattern.length() - 1);
            }
            char ch;
            int code = -1;
            while ((code = in.read()) != -1) {
                ch = (char)code;
                sb.append(ch);
                //匹配到结束标识时返回结果
                if (flag) {
                    //newly add && !sb.toString().endsWith("Seq"+pattern) to exclude "Seq#..."
                    if (ch == lastChar && sb.toString().endsWith(pattern) && !sb.toString().endsWith("Seq"+pattern)) {
                        return sb.toString();
                    }
                }else{
                    //如果没指定结束标识,匹配到默认结束标识字符时返回结果
                    if(ch == promptChar) {
                        return sb.toString();
                    }
                }
                //登录失败时返回结果
                if(sb.toString().contains("Login Failed")){
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    /**
     * 发送命令
     */
    public void write(String value) {
        try {
            out.println(value);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 发送命令,返回执行结果
     */
    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接
     */
    public void distinct(){
        try {
            if(telnet!=null&&!telnet.isConnected()) {
                telnet.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrompt(String prompt) {
        if(prompt!=null){
            this.prompt = prompt;
            this.promptChar = prompt.charAt(prompt.length()-1);
        }
    }

    public static void main(String[] args) {
        TelnetClient telnet = new TelnetClient("VT220","#");	//Windows,用VT220,否则会乱码
        if(telnet.login("192.168.10.1", 23, "CISCO")){
            System.out.println("login");
            String rs = telnet.sendCommand("sh ip route");
            try {
                rs = new String(rs.getBytes(StandardCharsets.ISO_8859_1),"GBK");	//转一下编码
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //去掉换行后的提示符
            rs=rs.split("\r\n")[0];
            System.out.println(rs);
        }
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
