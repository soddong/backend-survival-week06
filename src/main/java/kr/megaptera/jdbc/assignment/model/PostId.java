package kr.megaptera.jdbc.assignment.model;

import java.util.Objects;

public class PostId {
    private String value;

    public PostId(String value) {
        this.value = value;
    }

    public static PostId of(String value){
        return new PostId(value);
    }

    @Override
    public String toString(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return Objects.equals(value, postId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
