package com.code.payload.requests;

import com.code.payload.responses.DocumenteCandidat;

import java.util.List;

public class DocCheckForm {
    public List<DocumenteCandidat> docs;

    public DocumenteCandidat getDocs(int index) {
        return docs.get(index);
    }

    public List<DocumenteCandidat> getDocs() {return docs;}
    public void setDocs(List<DocumenteCandidat> docs) {
        this.docs = docs;
    }
}