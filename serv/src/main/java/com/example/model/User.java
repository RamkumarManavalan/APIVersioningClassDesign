package com.example.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class User {
        // TBD: This class needs to be enhanced to use @XmlAccessorType so we can get rid of public data members
	public String id = null;
	public String name = null;
	public String country = null;

        public static String getTableName() {
                return "user";
        }
}
