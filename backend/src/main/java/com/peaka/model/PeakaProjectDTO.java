package com.peaka.model;

import java.util.ArrayList;

public class PeakaProjectDTO {
    private String id;
    private String owner;
    private String name;
    private String description = null;
    private String domain;
    private String previewURL;
    private float version;
    private boolean deleted;
    private String parent = null;
    private String invitedBy = null;
    private String createdAt;
    private String updatedAt;
    private String deletedAt = null;
    private boolean disabled;
    private boolean internalApp;
    private boolean template;
    ArrayList<Object> roles = new ArrayList<Object>();


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDomain() {
        return domain;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public float getVersion() {
        return version;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public String getParent() {
        return parent;
    }

    public String getInvitedBy() {
        return invitedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public boolean getInternalApp() {
        return internalApp;
    }

    public boolean getTemplate() {
        return template;
    }

    // Setter Methods

    public void setId( String id ) {
        this.id = id;
    }

    public void setOwner( String owner ) {
        this.owner = owner;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public void setDomain( String domain ) {
        this.domain = domain;
    }

    public void setPreviewURL( String previewURL ) {
        this.previewURL = previewURL;
    }

    public void setVersion( float version ) {
        this.version = version;
    }

    public void setDeleted( boolean deleted ) {
        this.deleted = deleted;
    }

    public void setParent( String parent ) {
        this.parent = parent;
    }

    public void setInvitedBy( String invitedBy ) {
        this.invitedBy = invitedBy;
    }

    public void setCreatedAt( String createdAt ) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt( String updatedAt ) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt( String deletedAt ) {
        this.deletedAt = deletedAt;
    }

    public void setDisabled( boolean disabled ) {
        this.disabled = disabled;
    }

    public void setInternalApp( boolean internalApp ) {
        this.internalApp = internalApp;
    }

    public void setTemplate( boolean template ) {
        this.template = template;
    }
}