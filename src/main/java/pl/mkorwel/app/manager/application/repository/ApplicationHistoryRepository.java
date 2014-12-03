package pl.mkorwel.app.manager.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.mkorwel.app.manager.application.domain.ApplicationHistory;

@Repository
public interface ApplicationHistoryRepository extends CrudRepository<ApplicationHistory, Long> {

}
