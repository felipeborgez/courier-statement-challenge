package com.skipthedishes.challenge.business.repositories;

import com.skipthedishes.challenge.api.events.DeliveryCreated;
import com.skipthedishes.challenge.business.old_entities.Delivery;

public interface DeliveryRepository {

    public int save(Delivery delivery);
}
