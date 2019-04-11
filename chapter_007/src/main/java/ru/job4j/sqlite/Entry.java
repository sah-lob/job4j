package ru.job4j.sqlite;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {

    @XmlElement
    private int field;

    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getField() {
        return this.field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entry entry = (Entry) o;

        return field == entry.field;
    }

    @Override
    public int hashCode() {
        return field;
    }
}