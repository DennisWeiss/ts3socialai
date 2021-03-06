package com.weissdennis.tsas.tsups.api;

import com.weissdennis.tsas.tsups.model.TS3UserGraphLaplacianEigenDecomposition;
import com.weissdennis.tsas.tsups.service.SpectralClusteringService;
import org.ejml.data.Complex64F;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/ts3/spectral-clustering")
public class SpectralClusteringController {

    private final SpectralClusteringService spectralClusteringService;

    @Autowired
    public SpectralClusteringController(SpectralClusteringService spectralClusteringService) {
        this.spectralClusteringService = spectralClusteringService;
    }

    @RequestMapping(path = "/eigendecomposition", method = RequestMethod.GET)
    public HttpEntity<TS3UserGraphLaplacianEigenDecomposition> getEigenDecomposition(
            @RequestParam(required = false) Double minRelation
    ) {
        if (minRelation == null) {
            minRelation = 0d;
        }
        return new ResponseEntity<>(spectralClusteringService.getEigenDecomposition(minRelation), HttpStatus.OK);
    }

    @RequestMapping(path = "/eigenvalues", method = RequestMethod.GET)
    public HttpEntity<List<Complex64F>> getEigenValues(@RequestParam(required = false) Double minRelation) {
        if (minRelation == null) {
            minRelation = 0d;
        }
        return new ResponseEntity<>(spectralClusteringService.getEigenValues(minRelation), HttpStatus.OK);
    }

}
