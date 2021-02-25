package com.example.aldokana.Model;

import java.util.List;

public class AllRegionsModel {
    List<RegionsModel> regions;

    public AllRegionsModel(List<RegionsModel> regions) {
        this.regions = regions;
    }

    public List<RegionsModel> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionsModel> regions) {
        this.regions = regions;
    }
}
