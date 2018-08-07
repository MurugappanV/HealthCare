package com.fine_fettle.models;

import java.io.Serializable;

public class PrescriptionModel implements Serializable {
    public String hospital1,hospital2,hospital3,hospital4,hospital5;
    public String date1,date2,date3,date4,date5;
    public String medic1,medic2,medic3,medic4,medic5;
    public String issue1,issue2,issue3,issue4,issue5;
    public String docname1,docname2,docname3,docname4,docname5;
    public String pname;



    public PrescriptionModel(String p_name,String hospital1,String hospital2,String hospital3,String hospital4,String hospital5,
                             String date1,String date2,String date3,String date4,String date5,String medic1,String medic2,String medic3,String medic4,
                                String medic5,String issue1,String issue2,String issue3,String issue4,String issue5,
                             String docname1,String docname2,String docname3,String docname4,String docname5) {
        this.hospital1 = hospital1;
        this.hospital2 = hospital2;
        this.hospital3 = hospital3;
        this.hospital4 = hospital4;
        this.hospital5 = hospital5;

        this.date1=date1;
        this.date2=date2;
        this.date3=date3;
        this.date4=date4;
        this.date5=date5;

        this.medic1=medic1;
        this.medic2=medic2;
        this.medic3=medic3;
        this.medic4=medic4;
        this.medic5=medic5;

        this.issue1=issue1;
        this.issue2=issue2;
        this.issue3=issue3;
        this.issue4=issue4;
        this.issue5=issue5;

        this.docname1=docname1;
        this.docname2=docname2;
        this.docname3=docname3;
        this.docname4=docname4;
        this.docname5=docname5;

        this.pname=p_name;




    }

    public String getHospital_name1() {
        return hospital1;
    }
    public String getHospital_name2() {
        return hospital1;
    }
    public String getHospital_name3() {
        return hospital1;
    } public String getHospital_name4() {
        return hospital1;
    } public String getHospital_name5() {
        return hospital1;
    }



    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }

    public String getHospital_city() {
        return hospital_city;
    }

    public void setHospital_city(String hospital_city) {
        this.hospital_city = hospital_city;
    }

    public String getHospital_pincode() {
        return hospital_pincode;
    }

    public void setHospital_pincode(String hospital_pincode) {
        this.hospital_pincode = hospital_pincode;
    }
}
