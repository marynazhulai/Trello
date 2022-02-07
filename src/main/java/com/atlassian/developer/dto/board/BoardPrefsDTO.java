package com.atlassian.developer.dto.board;

public class BoardPrefsDTO {


     private String permissionLevel;

     private String hideVotes;

     private Boolean canBePublic;

     public String getPermissionLevel() {
         return permissionLevel;
     }

    public void setPermissionLevel(final String permissionLevel) {
         this.permissionLevel = permissionLevel;
     }

    public String getHideVotes() {
        return hideVotes;
    }

    public void setHideVotes(final String hideVotes) {
        this.hideVotes = hideVotes;
     }

    public Boolean getCanBePublic() {
         return canBePublic;
     }

    public void setCanBePublic(final Boolean canBePublic) {
        this.canBePublic = canBePublic;
        }
}
