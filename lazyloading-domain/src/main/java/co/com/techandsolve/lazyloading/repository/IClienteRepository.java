package co.com.techandsolve.lazyloading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import co.com.techandsolve.lazyloading.domain.Cliente;

@Eager
public interface IClienteRepository extends JpaRepository<Cliente,Long> {

}
