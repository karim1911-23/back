package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Site;
import dislog.cs.cs.repository.SiteRepo;

@Service
public class SiteService {

    @Autowired
    private SiteRepo siteRepo;

    public Site create(Site activite) {
        return siteRepo.save(activite);
    }

    public List<Site> getAll() {
        return siteRepo.findByActive(true);
    }

    public Site getById(Long id) {
        return siteRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Activite not found : " + id));
    }

    public Site delete(Long id) {
        Site activite = this.getById(id);
        activite.setActive(false);
        return siteRepo.save(activite);
    }

    public Site getBySite(String site) {
        return siteRepo.findBySite(site);
    }

    public Long countActive() {
        return siteRepo.countActive();
    }
}
