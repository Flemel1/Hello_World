package com.example.programandroid_1.data;

public class FireStoreItem {
    private String documentID;
    private String nim;
    private String nama;
    private String noHp;

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
