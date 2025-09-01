package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Region;
import dislog.cs.cs.repository.RegionRepo;

@Service
public class RegionService {

    @Autowired
    private RegionRepo regionRepo;

    public Region createRegion(Region region) {
        return regionRepo.save(region);
    }

    public Region updateRegion(Region region) {
        return regionRepo.save(region);
    }

    public Region getRegionById(Long id) {
        return regionRepo.findById(id).orElse(null);
    }

    public List<Region> getAllRegions() {
        return regionRepo.findAll();
    }

}
