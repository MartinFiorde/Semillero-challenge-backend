package ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CRUDServiceInterface<D> {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public D save(D d) throws ServiceRuntimeException;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public D edit(D d) throws ServiceRuntimeException;

    @Transactional(readOnly = true)
    public D getOne(String id);

    @Transactional(readOnly = true)
    public List<D> getAllActives();

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public D deactivate(String id) throws ServiceRuntimeException;
}