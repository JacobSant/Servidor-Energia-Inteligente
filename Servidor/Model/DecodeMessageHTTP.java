package Model;

public class DecodeMessageHTTP {
    private String method;
    private String path;
    private String parameter;
    private String versionHTTP = "HTTP/1.1";
    private String codeStatus = "200";
    private String messageStatus = "OK";
    private String header = "Host: LocalHost:4000";
    private String body = "Body line 1";

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public void setMessageHTTP(String versionHTTP, String codeStatus, String header, String body){
        this.versionHTTP = versionHTTP;
        this.codeStatus = codeStatus;
        this.header = header;
        this.body = body;
    }

    public String createMessageHTTP(){
        String message = this.versionHTTP+" "+this.codeStatus+" "+this.messageStatus+"\r\n"+this.header+"\r\n\r\n"+this.body;
        return message;
    }

    public void decodeMessage(String message){
        String[] splitBody = message.split("\r\n\r\n");
        this.body = splitBody[1];

        String[] splitLines = splitBody[0].split("\r\n");
        String[] line = splitLines[0].split(" ");
        this.method = line[0];

        String[] path_parameter = line[1].split("\\?");
        this.path = path_parameter[0];
        this.parameter = path_parameter[1];

        this.versionHTTP = line[2];

        this.header = splitLines[1];
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }
    public String getVersionHTTP() {
        return versionHTTP;
    }

    public void setVersionHTTP(String versionHTTP) {
        this.versionHTTP = versionHTTP;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }




}
