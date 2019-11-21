package common.study.option;

import java.io.Serializable;

public class ParsedEntity<E, T> implements Serializable {
    private static final long serialVersionUID = 7754705068651961068L;
    private E entity;
    private T parsed;

    public ParsedEntity() {
    }

    public E getEntity() {
        return this.entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public T getParsed() {
        return this.parsed;
    }

    public void setParsed(T parsed) {
        this.parsed = parsed;
    }
}