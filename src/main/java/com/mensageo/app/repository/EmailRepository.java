package com.mensageo.app.repository;

import com.mensageo.app.model.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {
}
