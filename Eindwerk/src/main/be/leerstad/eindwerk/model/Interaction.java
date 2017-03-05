package main.be.leerstad.eindwerk.model;

import java.sql.Time;

public abstract class Interaction<T> {
    private static final int MAX_TIME_BETWEEN_REQUESTS_IN_MILLISEC = 20*60*1000;

    private String id;
    private LogFile logFile;
    private String ipAddress;
    private Time time;
    private Integer totalTime;
    private Integer transferredBytes;
    private Integer numberOfRequests;

    public Interaction() {
        this.id = "";
        this.logFile = new LogFile();
        this.ipAddress = "";
        this.time = new Time(0);
        this.totalTime = 1;
        this.transferredBytes = 0;
        this.numberOfRequests = 1;
    }

    public Interaction(LogFile logFile, String ipAddress, Time time, Integer transferredBytes) {
        this.id = "";
        this.logFile = logFile;
        this.ipAddress = ipAddress;
        this.time = time;
        this.totalTime = 1;
        this.transferredBytes = transferredBytes;
        this.numberOfRequests = 1;
    }

    public Interaction(String id, LogFile logFile, String ipAddress, Time time, Integer totalTime,
                       Integer transferredBytes, Integer numberOfRequests) {
        this.id = id;
        this.logFile = logFile;
        this.ipAddress = ipAddress;
        this.time = time;
        this.totalTime = totalTime;
        this.transferredBytes = transferredBytes;
        this.numberOfRequests = numberOfRequests;
    }

    public static int getMaxTimeBetweenRequestsInMillisec() {
        return MAX_TIME_BETWEEN_REQUESTS_IN_MILLISEC;
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

    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
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

    public abstract T concatenate(T interaction);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interaction interaction = (Interaction) o;

        return this.getTime().getTime() + this.getTotalTime() + MAX_TIME_BETWEEN_REQUESTS_IN_MILLISEC
                >= interaction.getTime().getTime();
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
                ", totalTime=" + totalTime +
                ", transferredBytes=" + transferredBytes +
                ", numberOfRequests=" + numberOfRequests +
                '}';
    }
}
