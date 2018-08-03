package com.example.surya.fine_fettle.model;


public class Patienthistory {

    String mName, mAge, mDate, mTime,mProblem,mStatus,mDocId,mPatientID,mDoc_Status;


    public Patienthistory(String name, String age, String date,String time, String problem, String status) {
        this.mName = name;
        this.mAge = age;
        this.mDate = date;
        this.mTime = time;
        this.mProblem = problem;
        this.mStatus = status;
    }

    public Patienthistory(String name, String age, String date,String time, String problem, String patient_id, String Doc_id,String  Doc_status) {
        this.mName = name;
        this.mAge = age;
        this.mDate = date;
        this.mTime = time;
        this.mProblem = problem;
        this.mPatientID = patient_id;
        this.mDocId = Doc_id;
        this.mDoc_Status = Doc_status;
    }

    public String getmDoc_Status() {
        return mDoc_Status;
    }

    public void setmDoc_Status(String mDoc_Status) {
        this.mDoc_Status = mDoc_Status;
    }

    public String getmPatientID() {
        return mPatientID;
    }

    public void setmPatientID(String mPatientID) {
        this.mPatientID = mPatientID;
    }

    public String getmDocId() {
        return mDocId;
    }

    public void setmDocId(String mDocId) {
        this.mDocId = mDocId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAge() {
        return mAge;
    }

    public void setmAge(String mAge) {
        this.mAge = mAge;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmProblem() {
        return mProblem;
    }

    public void setmProblem(String mProblem) {
        this.mProblem = mProblem;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}
