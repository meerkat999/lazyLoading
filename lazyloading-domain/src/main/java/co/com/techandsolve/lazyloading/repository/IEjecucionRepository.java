package co.com.techandsolve.lazyloading.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.cdi.Eager;

import co.com.techandsolve.lazyloading.domain.Ejecucion;

@Eager
public interface IEjecucionRepository extends PagingAndSortingRepository<Ejecucion,Integer> {

}