package by.makarov.wsmgr.entety;

/**
 * Created by Andrey Makarov on 24.04.2016.
 */
public class Ping {
    String message;
    long pingId;

    public Ping(String message, long pingId) {
        this.message = message;
        this.pingId = pingId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getPingId() {
        return pingId;
    }

    public void setPingId(long pingId) {
        this.pingId = pingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ping ping = (Ping) o;

        if (pingId != ping.pingId) return false;
        return message != null ? message.equals(ping.message) : ping.message == null;

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (int) (pingId ^ (pingId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Ping{" +
                "message='" + message + '\'' +
                ", pingId=" + pingId +
                '}';
    }
}
