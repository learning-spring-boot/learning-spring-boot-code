package learningspringboot;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

// tag::slimmed[]
public interface TeammateRepository extends CrudRepository<Teammate, BigInteger> {}
// end::slimmed[]
