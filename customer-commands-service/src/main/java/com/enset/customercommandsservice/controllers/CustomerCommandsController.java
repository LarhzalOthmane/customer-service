package com.enset.customercommandsservice.controllers;

import com.enset.commonapi.commands.CreateCustomerCommand;
import com.enset.commonapi.dtos.CreateCustomerRequestDTO;
import com.enset.commonapi.dtos.UpdateCustomerRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/customers")
@Slf4j
@AllArgsConstructor
public class CustomerCommandsController {

    private CommandGateway gateway;

    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDTO requestDTO) {
        CreateCustomerCommand command = new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                requestDTO.getName(),
                requestDTO.getEmail()
        );
        return gateway.send(command);
    }

    @PutMapping("/{id}")
    public CompletableFuture<String> updateCustomer(
            @RequestBody UpdateCustomerRequestDTO requestDTO,
            @PathVariable String id
    ) {
        CreateCustomerCommand command = new CreateCustomerCommand(
                id,
                requestDTO.getName(),
                requestDTO.getEmail()
        );
        return gateway.send(command);
    }

    @GetMapping("/eventstore/{id}")
    public Stream eventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }

}

