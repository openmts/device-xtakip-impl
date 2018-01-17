package com.openvehicletracking.protocols.gt100.login;

import com.openvehicletracking.core.ConnectionHolder;
import com.openvehicletracking.core.Device;
import com.openvehicletracking.core.Reply;
import com.openvehicletracking.core.protocol.Message;
import com.openvehicletracking.core.protocol.MessageHandler;
import com.openvehicletracking.protocols.gt100.GT100Device;

import java.nio.ByteBuffer;
import java.util.Arrays;
public class LoginMessageHandler implements MessageHandler {

    @Override
    public boolean isMatch(Object msg) {
        if (!(msg instanceof byte[])) {
            return false;
        }

        ByteBuffer buffer = ByteBuffer.wrap((byte[]) msg);
        short header = buffer.getShort();
        if (header != 0x7878) {
            return false;
        }

        byte length = buffer.get();
        byte type = buffer.get();
        return type == LoginMessage.TYPE;
    }

    @Override
    public Message handle(Object msg, ConnectionHolder<?> connectionHolder) {
        byte[] message = (byte[]) msg;


        byte[] header = Arrays.copyOfRange(message, 0, 2);
        byte[] lenght = Arrays.copyOfRange(message, 2, 3);
        byte[] type = Arrays.copyOfRange(message, 3, 4);
        byte[] serial = Arrays.copyOfRange(message, 4, 12);

        Device device = new GT100Device(toHex(serial));
        LoginMessage loginMessage = new LoginMessage(device, message);

        ByteBuffer response = ByteBuffer.allocate(10);
        response.put((byte) 0x78)
                .put((byte) 0x78)
                .put((byte) 0x05)
                .put((byte) 0x01)
                .put((byte) 0x00)
                .put((byte) 0x01)
                .put((byte) 0xD9)
                .put((byte) 0xDC)
                .put((byte) 0x0D)
                .put((byte) 0x0A);

        connectionHolder.write(new Reply(response.array()));
        return loginMessage;
    }


    protected static String toHex(byte[] in) {
        StringBuilder builder = new StringBuilder();
        for (byte anIn : in) {
            builder.append(String.format("%x", anIn));
        }

        return builder.toString();
    }
}