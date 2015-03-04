package me.yeojoy.studio.dto;

/**
 * Created by yeojoy on 15. 1. 6..
 */
public class NotiData {
    // from server
    private int id;
    private int type;
    private String message;
    private int wakelockId;
    private String collapseKey;
    private String from;
    
    // set data
    private int iconResourceId;
    private String title;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private String summary;

    public NotiData() {}

    public NotiData(int id, int type, String message, int wakelockId, String collapseKey, String from) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.wakelockId = wakelockId;
        this.collapseKey = collapseKey;
        this.from = from;
    }

    public NotiData(int id, int type, String message, int wakelockId, String collapseKey, String from, int iconResourceId, String title) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.wakelockId = wakelockId;
        this.collapseKey = collapseKey;
        this.from = from;
        this.iconResourceId = iconResourceId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getWakelockId() {
        return wakelockId;
    }

    public void setWakelockId(int wakelockId) {
        this.wakelockId = wakelockId;
    }

    public String getCollapseKey() {
        return collapseKey;
    }

    public void setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NotiData{" +
                "id=" + id +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", wakelockId=" + wakelockId +
                ", collapseKey='" + collapseKey + '\'' +
                ", from='" + from + '\'' +
                ", iconResourceId=" + iconResourceId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}

