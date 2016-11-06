package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityEntity implements Serializable {

    @Nullable
    private String id;

    @Nullable
    private String type;

    @Nullable
    private String status;

    @Nullable
    private String context;

    @JsonCreator
    public ActivityEntity(@JsonProperty("id") final String id, @JsonProperty("type") final String type, @JsonProperty("status") final String status, @JsonProperty("context") final String context) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.context = context;
    }
}
