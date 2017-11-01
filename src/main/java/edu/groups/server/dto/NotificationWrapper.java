package edu.groups.server.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dawid on 01.11.2017 at 13:38.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationWrapper {
    @SerializedName("to")
    private String destination;
    @SerializedName("data")
    private Notification notification;
}
