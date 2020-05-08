package com.mensageo.app.repository;

import com.mensageo.app.model.HospitalNeeds;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HospitalNeedsRepository extends CrudRepository<HospitalNeeds, Long> {

    public List<HospitalNeeds> findByHospitalId(long hospitalId);
    public List<HospitalNeeds> findByProductId(long productId);
}
