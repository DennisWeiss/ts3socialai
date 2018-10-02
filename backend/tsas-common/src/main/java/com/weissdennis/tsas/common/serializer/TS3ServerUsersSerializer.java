package com.weissdennis.tsas.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.weissdennis.tsas.common.ts3users.TS3ServerUsers;

import java.io.IOException;

public class TS3ServerUsersSerializer extends StdSerializer<TS3ServerUsers> {

    public TS3ServerUsersSerializer() {
        this(null);
    }

    public TS3ServerUsersSerializer(Class<TS3ServerUsers> t) {
        super(t);
    }

    @Override
    public void serialize(TS3ServerUsers ts3ServerUsers, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("dateTime", ts3ServerUsers.getDateTime().toEpochMilli());
        jsonGenerator.writeNumberField("users", ts3ServerUsers.getUsers());
        jsonGenerator.writeEndObject();
    }
}
