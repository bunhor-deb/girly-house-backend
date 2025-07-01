package com.bn.girlyhousebackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBody <T extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = -1821852858721739997L;

    @JsonProperty("status")
    @SerializedName("status")
    private String status;

    @JsonProperty("error_code")
    @SerializedName("error_code")
    private String errorCode;

    @JsonProperty("error_message")
    @SerializedName("error_message")
    private String errorMessage;

    @JsonProperty("data")
    @SerializedName("data")
    private T data;

    @JsonProperty("trace")
    @SerializedName("trace")
    private Trace trace;

    @Setter @Getter
    public static class Trace implements Serializable {
        @Serial
        private static final long serialVersionUID = -6423937063197443843L;

        @JsonProperty("trace_id")
        @SerializedName("trace_id")
        private String traceId;

        @JsonProperty("created_at")
        @SerializedName("created_at")
        private String createdAt;
    }

    public static <T extends Serializable> ResponseBody<T> create(T data, String status) {
        var body = new ResponseBody<T>();
        body.setStatus(status);
        body.setErrorCode("");
        body.setErrorMessage("");
        body.setData(data);
        body.setTrace(getTrace());
        return body;
    }

    public static Trace getTrace() {
        var trace = new Trace();
        trace.setTraceId(Thread.currentThread().getName());
        trace.setCreatedAt(LocalDateTime.now().toString());
        return trace;
    }

}
