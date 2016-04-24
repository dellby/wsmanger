package by.makarov.wsmgr.entety;

/**
 * Created by Andrey Makarov on 24.04.2016.
 */
public class Pong {
    private String message;
    private long pingId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return pingId;
    }

    public void setId(long id) {
        this.pingId = id;
    }

    @Override
    public String toString() {
        return "Pong{" +
                "message='" + message + '\'' +
                ", pingId=" + pingId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pong pong = (Pong) o;

        if (pingId != pong.pingId) return false;
        return message != null ? message.equals(pong.message) : pong.message == null;

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (int) (pingId ^ (pingId >>> 32));
        return result;
    }
}
