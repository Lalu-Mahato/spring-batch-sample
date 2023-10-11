package com.example.springbatchsample.prospect;

import java.util.List;

import com.example.springbatchsample.prospect.entity.Prospect;

public class ProspectService {

    public void processProspects(List<? extends Prospect> prospects) {
        for (Prospect prospect : prospects) {
            System.out.println("Processing prospect: " + prospect);
        }
    }

}
