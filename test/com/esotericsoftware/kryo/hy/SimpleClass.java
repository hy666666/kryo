package com.esotericsoftware.kryo.hy;

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleClass {

    @Tag(1)
    private String s0;

    @Tag(2)
    private int i0;

}
