package com.code.payload.requests;

import com.code.payload.requests.OptiuniRequest;

import java.util.ArrayList;
import java.util.List;

public class OptiuniARequest {
    private List<OptiuniRequest> optiuni;
    public void addOptiuni(OptiuniRequest a){
        this.optiuni.add(a);
    }

    public List<OptiuniRequest> getOptiuni() {
        return optiuni;
    }

    public void setOptiuni(List<OptiuniRequest> optiuni) {
        this.optiuni = optiuni;
    }

    public OptiuniARequest(List<OptiuniRequest> opt) {
        this.optiuni = opt;
    }

    public OptiuniARequest() {
        this.optiuni=new ArrayList<OptiuniRequest>();
    }
}
