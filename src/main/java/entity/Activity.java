package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Activity implements Serializable {

    private ActivityEntity actor;

    private String verb;

    private ActivityEntity object;

    @Nullable
    private ActivityEntity target;

    @JsonCreator
    public Activity(@JsonProperty("actor") final ActivityEntity actor, @JsonProperty("object") final ActivityEntity object, @JsonProperty("target") final ActivityEntity target, @JsonProperty("verb") final String verb) {
        this.actor = actor;
        this.verb = verb;
        this.object = object;
        this.target = target;
    }
}
