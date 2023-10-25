package com.esotericsoftware.kryo.hy;

import com.alibaba.fastjson.JSON;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.junit.jupiter.api.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

public class Test1 {

    private static Kryo kryo = null;
    private Kryo getKryo() {
        if (kryo != null) {
            return kryo;
        }
        kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setDefaultSerializer(TaggedFieldSerializer.class);
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        return kryo;
    }

    public byte[] serialize(Object obj) {
        try (Output output = new Output(1024, -1);) {
            getKryo().writeObjectOrNull(output, obj);
            return output.toBytes();
        }
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Kryo kryo = getKryo();
        try(Input input = new Input(bytes)) {
            T obj = kryo.readObjectOrNull0(input, clazz);
            return obj;
        }
    }


    /**
     * 普通类
     */
    @Test
    public void test0() {
        SimpleClass obj = new SimpleClass();
        obj.setS0("str");
        obj.setI0(123);
        byte[] bs = serialize(obj);
        SimpleClass obj1 = deserialize(bs, SimpleClass.class);
        System.out.println(obj1);
    }

    /**
     * 改造1: 写入空数据
     */
    @Test
    public void test1() {
        SimpleClass obj = null;
        byte[] bs = serialize(obj);
        SimpleClass obj1 = deserialize(bs, SimpleClass.class);
        System.out.println(obj1);

        SimpleClass obj2 = deserialize(new byte[0], SimpleClass.class);
        System.out.println(obj2);
    }



}
