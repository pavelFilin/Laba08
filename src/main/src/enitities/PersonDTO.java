package enitities;

import java.util.*;

public class PersonDTO {
    public UUID id;

    public String firstName;
    public String secondName;
    public String middleName;

    public String school;

    public GregorianCalendar attendDate;
    public GregorianCalendar endDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public GregorianCalendar getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(GregorianCalendar attendDate) {
        this.attendDate = attendDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }
}
