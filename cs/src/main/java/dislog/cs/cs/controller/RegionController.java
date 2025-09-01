package dislog.cs.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Region;
import dislog.cs.cs.service.RegionService;

@RestController
@RequestMapping("/api/admin/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    public Region create(@RequestBody Region region) {
        return regionService.createRegion(region);
    }

    @PutMapping("/update")
    public Region update(@RequestBody Region region) {
        return regionService.updateRegion(region);
    }

    @GetMapping("/{id}")
    public Region getById(@PathVariable Long id) {
        return regionService.getRegionById(id);
    }

    @GetMapping("/all")
    public List<Region> getAll() {
        return regionService.getAllRegions();
    }

}
