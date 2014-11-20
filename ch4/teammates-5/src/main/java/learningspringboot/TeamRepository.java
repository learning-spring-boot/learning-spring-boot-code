package learningspringboot;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

// tag::slimmed[]
public interface TeamRepository extends CrudRepository<Team, BigInteger> {}
// end::slimmed[]
