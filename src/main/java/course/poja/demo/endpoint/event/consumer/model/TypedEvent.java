package course.poja.demo.endpoint.event.consumer.model;

import course.poja.demo.PojaGenerated;
import course.poja.demo.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
