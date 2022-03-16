package net.sahin.springvw.campaign;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class AdditionalTask implements Serializable {
    public String callOut;
    public String urlToComplete;
    public String icon;
    public boolean isClicked;

    public AdditionalTask() {

    }

    public AdditionalTask(String callOut, String urlToComplete, String icon) {
        this.callOut = callOut;
        this.urlToComplete = urlToComplete;
        this.icon = icon;
    }

    public AdditionalTask(String callOut, String urlToComplete, String icon, boolean isClicked) {
        this.callOut = callOut;
        this.urlToComplete = urlToComplete;
        this.icon = icon;
        this.isClicked = isClicked;
    }
}
