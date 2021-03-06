package com.openvehicletracking.protocols.gt100.heartbeat;

import com.openvehicletracking.core.Device;
import com.openvehicletracking.core.DeviceState;
import com.openvehicletracking.core.Reply;
import com.openvehicletracking.core.exception.StateCreateNotSupportException;
import com.openvehicletracking.core.json.GsonFactory;
import com.openvehicletracking.core.protocol.Message;
import com.openvehicletracking.protocols.gt100.GT100Device;
import com.openvehicletracking.protocols.gt100.Gt100Protocol;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class HeartbeatMessage implements Message {

    private String raw;
    private HashMap<String, Object> attributes = new HashMap<>();

    public HeartbeatMessage(String raw, HashMap<String, Object> attributes) {
        this.raw = raw;
        this.attributes = attributes;
    }

    @Override
    public Object getRaw() {
        return raw;
    }

    @Nullable
    @Override
    public Device getDevice() {
        return null;
    }

    @Override
    public Date getDate() {
        return new Date();
    }

    @Override
    public Optional<HashMap<String, Object>> getAttributes() {
        return Optional.of(attributes);
    }

    @Override
    public void reply(Reply reply) {

    }

    @Override
    public String getProtocolName() {
        return Gt100Protocol.NAME;
    }

    @Override
    public int getType() {
        return 0x13;
    }

    @Override
    public String asJson() {
        return GsonFactory.getGson().toJson(this);
    }

    @Override
    public DeviceState createState() throws StateCreateNotSupportException {
        throw new StateCreateNotSupportException("HeartbeatMessage doesn't support to creating state");
    }
}
