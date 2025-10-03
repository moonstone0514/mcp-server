package org.example.mcpserver.dto;

public class McpPayload {
    private String message;

    public McpPayload() {}

    public McpPayload(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
