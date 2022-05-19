package LearnUp.PushkaHW_34.controller;

import LearnUp.PushkaHW_34.dao.entity.Client;
import LearnUp.PushkaHW_34.dao.filter.ClientFilter;
import LearnUp.PushkaHW_34.service.ClientService;
import LearnUp.PushkaHW_34.view.ClientView;
import LearnUp.PushkaHW_34.view.mapper.ClientViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("rest/client")
public class ClientControllerRest {

    private final ClientService clientService;
    private final ClientViewMapper clientViewMapper;

    @Secured({"ROLE_USER"})
    @GetMapping
    public List<ClientView> getBuyers(
            @RequestParam(value = "dateOfBirth", required = false) LocalDate dateOfBirth,
            @RequestParam(value = "dateRegistration", required = false) LocalDate dateRegistration,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName
    ) {
        return clientService.getAllBuyers(new ClientFilter(dateOfBirth, dateRegistration, firstName, lastName))
                .stream()
                .map(clientViewMapper::mapClientToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/{id}")
    public ClientView getBuyerById(@PathVariable("id") Long id) {
        return clientViewMapper.mapClientToView(clientService.getClientById(id));
    }

    @Secured({"ROLE_USER"})
    @PostMapping
    public ClientView createBuyer(@RequestBody ClientView clientView) {
        if (clientView.getId() != null) {
            throw new EntityExistsException(
                    String.format("Buyers with id = %s already exist", clientView.getId())
            );
        }
        Client client = clientViewMapper.mapBuyersFromView(clientView);
        Client createClient = clientService.createClient(client);
        return clientViewMapper.mapClientToView(createClient);
    }

    @Secured({"ROLE_USER"})
    @PutMapping("/{id}")
    public ClientView updateBuyer(@PathVariable("id") Long id,
                                  @RequestBody ClientView buyersView) {
        if (buyersView.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(id, buyersView.getId())) {
            throw new RuntimeException("Entity has bad id");
        }
        Client client = clientService.getClientById(id);
        if (!client.getDateOfBirth().equals(buyersView.getDateOfBirth())) {
            client.setDateOfBirth(buyersView.getDateOfBirth());
        }
        if (!client.getDateRegistration().equals(buyersView.getDateRegistration())) {
            client.setDateRegistration(buyersView.getDateRegistration());
        }
        if (!client.getFirstName().equals(buyersView.getFirstName())) {
            client.setFirstName(buyersView.getFirstName());
        }
        if (!client.getLastName().equals(buyersView.getLastName())) {
            client.setLastName(buyersView.getLastName());
        }
        Client updateClient = clientService.updateClient(client);
        return clientViewMapper.mapClientToView(updateClient);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public Boolean deleteClient(@PathVariable("id") Long id) {
        return clientService.deleteClient(id);
    }

}
