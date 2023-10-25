package com.esotericsoftware.kryo.hy;

import com.alibaba.fastjson.JSON;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.junit.jupiter.api.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

public class Test0 {


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


    @Test
    public void testFastjson0() {
        SimpleClass obj = new SimpleClass();
        obj.setS0("str");
        obj.setI0(123);

        String s = JSON.toJSONString(obj);
        SimpleClass obj1 = JSON.parseObject(s, SimpleClass.class);
        System.out.println(obj1);
    }
}
