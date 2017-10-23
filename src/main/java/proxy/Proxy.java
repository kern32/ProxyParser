package proxy;

public class Proxy {
    private String ip;
    private long port;
    private Type type;

    public boolean isHttp(){
        return this.type != null && this.type.name().toString().toUpperCase().startsWith("HTTP");
    }

    public boolean isSocks(){
        return this.type != null && this.type.name().toString().toUpperCase().startsWith("SOCKS");
    }

    public Proxy(String ip, long port, Type type) {
        this.ip = ip;
        this.port = port;
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proxy proxy = (Proxy) o;

        if (getPort() != proxy.getPort()) return false;
        if (getIp() != null ? !getIp().equals(proxy.getIp()) : proxy.getIp() != null) return false;
        return getType() == proxy.getType();
    }

    @Override
    public int hashCode() {
        int result = getIp() != null ? getIp().hashCode() : 0;
        result = 31 * result + (int) (getPort() ^ (getPort() >>> 32));
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ip + ":" + port;
    }
}
