package com.esotericsoftware.kryo.hy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.junit.jupiter.api.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

public class Test1 {

    @Test
    public void test1() {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setDefaultSerializer(TaggedFieldSerializer.class);
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));

        Output output = new Output(1024, -1);
        String obj = "aaa";
        kryo.writeObject(output, obj);

        try(Input input = new Input(output.getBuffer())) {
            obj = kryo.readObject(input, obj.getClass());
            System.out.println(obj);
        }
        output.close();
    }
}
