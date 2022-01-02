package com.enset.customercommandsservice.aggregates;

import com.enset.commonapi.commands.CreateCustomerCommand;
import com.enset.commonapi.commands.UpdateCustomerCommand;
import com.enset.commonapi.events.CustomerCreatedEvent;
import com.enset.commonapi.events.CustomerUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String id;
    @Getter
    private String name;
    @Getter
    private String email;

    // Decision function
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        /*
         Some tests before firing the event
         */
        log.info("CreateCustomerCommand received!");
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getName(),
                command.getEmail()
        ));
    }

    @CommandHandler
    public void handle(UpdateCustomerCommand command) {
        /*
         Some tests before firing the event
         */
        AggregateLifecycle.apply(new CustomerUpdatedEvent(
                command.getId(),
                command.getName(),
                command.getEmail()
        ));
    }

    // Evolution function => mutating the app state
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        log.info("CustomerCreatedEvent fired!");
        this.id = event.getId();
        this.email = event.getEmail();
        this.name = event.getName();

    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event) {
        this.id = event.getId();
        this.email = event.getEmail();
        this.name = event.getName();
    }

}
