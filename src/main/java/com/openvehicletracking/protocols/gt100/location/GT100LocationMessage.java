package com.openvehicletracking.protocols.gt100.location;

import com.openvehicletracking.core.*;
import com.openvehicletracking.core.json.GsonFactory;
import com.openvehicletracking.protocols.BaseLocationMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class GT100LocationMessage extends BaseLocationMessage {

    private final int messageType = 0x22;

    public GT100LocationMessage(AbstractLocationMessageBuilder<?> builder) {
        super(builder);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public GpsStatus getStatus() {
        return gpsStatus;
    }

    @Override
    public Object getRaw() {
        return raw;
    }

    @Override
    public Device getDevice() {
        return device;
    }

    @Override
    public Date getDate() {
        return new Date(datetime);
    }

    @Override
    public Optional<HashMap<String, Object>> getAttributes() {
        return Optional.of(attributes);
    }

    @Override
    public void reply(Reply reply) {

    }

    @Override
    public String asJson() {
        return GsonFactory.getGson().toJson(this);
    }
}