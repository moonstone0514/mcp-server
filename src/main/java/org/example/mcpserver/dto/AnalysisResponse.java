package org.example.mcpserver.dto;

public class AnalysisResponse {
    private Long id;
    private String analysis;

    public AnalysisResponse() {}

    public AnalysisResponse(Long id, String analysis) {
        this.id = id;
        this.analysis = analysis;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAnalysis() { return analysis; }
    public void setAnalysis(String analysis) { this.analysis = analysis; }
}
