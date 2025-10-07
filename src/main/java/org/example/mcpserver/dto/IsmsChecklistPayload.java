package org.example.mcpserver.dto;

public class IsmsChecklistPayload {
    private String system;
    private String timestamp;
    private Integer userCount;
    private String lastPasswordChange;
    private Integer logRetentionDays;
    private Boolean logIntegrity;
    private String firewallStatus;
    private Boolean mfaEnabled;

    public IsmsChecklistPayload() {}

    // getter / setter 전부 생성
    public String getSystem() { return system; }
    public void setSystem(String system) { this.system = system; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public Integer getUserCount() { return userCount; }
    public void setUserCount(Integer userCount) { this.userCount = userCount; }

    public String getLastPasswordChange() { return lastPasswordChange; }
    public void setLastPasswordChange(String lastPasswordChange) { this.lastPasswordChange = lastPasswordChange; }

    public Integer getLogRetentionDays() { return logRetentionDays; }
    public void setLogRetentionDays(Integer logRetentionDays) { this.logRetentionDays = logRetentionDays; }

    public Boolean getLogIntegrity() { return logIntegrity; }
    public void setLogIntegrity(Boolean logIntegrity) { this.logIntegrity = logIntegrity; }

    public String getFirewallStatus() { return firewallStatus; }
    public void setFirewallStatus(String firewallStatus) { this.firewallStatus = firewallStatus; }

    public Boolean getMfaEnabled() { return mfaEnabled; }
    public void setMfaEnabled(Boolean mfaEnabled) { this.mfaEnabled = mfaEnabled; }
}

