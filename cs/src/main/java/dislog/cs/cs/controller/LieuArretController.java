package dislog.cs.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.repository.LieuArretRepo;

@RestController
@RequestMapping("/api/admin/lieu")
public class LieuArretController {
    
    @Autowired
    private LieuArretRepo lieuArretRepo;
}
