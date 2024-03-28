package com.obms.domain.envlinks;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author saurabh.shyam
 * @since Feb 24, 2021 4:42 PM
 */
@Entity
@Table(name = "Env_Links")
public class EnironmentSetupLinks {

    @Id
    private long linkId;
    private String keyName;
    private String keyValue;


    public long getLinkId() {
        return linkId;
    }

    public EnironmentSetupLinks(long linkId, String keyName, String keyValue) {
        this.linkId = linkId;
        this.keyName = keyName;
        this.keyValue = keyValue;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
