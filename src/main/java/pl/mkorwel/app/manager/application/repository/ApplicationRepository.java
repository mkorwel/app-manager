package pl.mkorwel.app.manager.application.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.mkorwel.app.manager.application.domain.Application;

@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {

}
