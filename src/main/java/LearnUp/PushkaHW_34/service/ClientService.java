package LearnUp.PushkaHW_34.service;

import LearnUp.PushkaHW_34.dao.entity.Client;
import LearnUp.PushkaHW_34.dao.filter.ClientFilter;
import LearnUp.PushkaHW_34.dao.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;

import static LearnUp.PushkaHW_34.dao.specification.ClientSpecification.byClientFilter;


@Slf4j
@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllBuyers(ClientFilter clientFilter) {
        Specification<Client> specification = Specification.where(byClientFilter(clientFilter));
        log.info("Request getAllUsers: {}", specification);
        return clientRepository.findAll(specification);
    }

    public Client createClient(Client bookStorage) {
        log.info("CreateClient: {}", bookStorage.toString());
        return clientRepository.save(bookStorage);
    }
    
    public Client getClientById(Long id) {
        log.info("Request getBuyersById: {}", id);
        return clientRepository.getClientById(id);
    }
    
    public Boolean deleteBuyer(Long id) {
        log.info("DeleteBuyer id: {}", id);
        clientRepository.delete(clientRepository.getById(id));
        return true;
    }
    
    public Client updateBuyer(Client client) {
        try {
            log.info("UpdateBuyer: {}", client.toString());
            return clientRepository.save(client);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for Buyer id {}", client.getId());
            throw e;
        }
    }
    
}
