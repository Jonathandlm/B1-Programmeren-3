package be.leerstad.eindwerk.model;

import java.time.Duration;
import java.time.LocalTime;

public abstract class Interaction<T extends Interaction> {
    private static final int MAX_TIME_BETWEEN_REQUESTS_IN_MINUTES = 20;

    private String id;
    private LogFile logFile;
    private String ipAddress;
    private LocalTime time;
    private long totalTimeInSec;
    private Integer transferredBytes;
    private Integer numberOfRequests;

    public Interaction() {
        this.id = "";
        this.logFile = new LogFile();
        this.ipAddress = "";
        this.time = LocalTime.now();
        this.totalTimeInSec = 1;
        this.transferredBytes = 0;
        this.numberOfRequests = 1;
    }

    public Interaction(LogFile logFile, String ipAddress, LocalTime time, Integer transferredBytes) {
        this.id = "";
        this.logFile = logFile;
        this.ipAddress = ipAddress;
        this.time = time;
        this.totalTimeInSec = 1;
        this.transferredBytes = transferredBytes;
        this.numberOfRequests = 1;
    }

    public Interaction(String id, LogFile logFile, String ipAddress, LocalTime time, Integer totalTimeInSec,
                       Integer transferredBytes, Integer numberOfRequests) {
        this.id = id;
        this.logFile = logFile;
        this.ipAddress = ipAddress;
        this.time = time;
        this.totalTimeInSec = totalTimeInSec;
        this.transferredBytes = transferredBytes;
        this.numberOfRequests = numberOfRequests;
    }

    public static int getMaxTimeBetweenRequestsInMillisec() {
        return MAX_TIME_BETWEEN_REQUESTS_IN_MINUTES;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public LogFile getLogFile() {
        return logFile;
    }
    public void setLogFile(LogFile logFile) {
        this.logFile = logFile;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public long getTotalTimeInSec() {
        return totalTimeInSec;
    }
    public void setTotalTimeInSec(long totalTimeInSec) {
        this.totalTimeInSec = totalTimeInSec;
    }

    public Integer getTransferredBytes() {
        return transferredBytes;
    }
    public void setTransferredBytes(Integer transferredBytes) {
        this.transferredBytes = transferredBytes;
    }

    public Integer getNumberOfRequests() {
        return numberOfRequests;
    }
    public void setNumberOfRequests(Integer numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

    public void concatenate(T interaction) {
        this.setTotalTimeInSec(Duration.between(this.getTime(), interaction.getTime()).getSeconds());
        this.setTransferredBytes(this.getTransferredBytes() + interaction.getTransferredBytes());
        this.setNumberOfRequests(this.getNumberOfRequests() + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interaction interaction = (Interaction) o;

        return Duration.between(this.getTime().plusSeconds(this.getTotalTimeInSec()), interaction.getTime()).toMinutes()
                <= MAX_TIME_BETWEEN_REQUESTS_IN_MINUTES ;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Interaction{" +
                "id='" + id + '\'' +
                ", logFile=" + logFile +
                ", ipAddress='" + ipAddress + '\'' +
                ", time=" + time +
                ", totalTimeInSec=" + totalTimeInSec +
                ", transferredBytes=" + transferredBytes +
                ", numberOfRequests=" + numberOfRequests +
                '}';
    }
}
