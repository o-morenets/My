/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : Group.java 100473
 * 19-Nov-20 21:23:48Z Oleksii_Morenets $
 */

package stackOverflow.studentsGroups;

public class Group {
    private int id;

    public Group(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id: " + id +
                '}';
    }
}
