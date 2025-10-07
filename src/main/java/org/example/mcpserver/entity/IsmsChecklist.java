package org.example.mcpserver.entity;

import jakarta.persistence.*;

@Entity
public class IsmsChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="system_name")   // ✅ 예약어 회피
    private String system;

    @Column(name="event_time")    // ✅ 예약어 회피
    private String timestamp;

    private Integer userCount;
    private String lastPasswordChange;
    private Integer logRetentionDays;
    private Boolean logIntegrity;
    private String firewallStatus;
    private Boolean mfaEnabled;

    // ✅ 기본 생성자
    public IsmsChecklist() {}

    // ✅ Getter/Setter
    public Long getId() { return id; }

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
