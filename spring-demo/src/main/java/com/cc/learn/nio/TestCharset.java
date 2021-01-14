package com.cc.learn.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/01/13
 */
public class TestCharset {

    //字符集
    @Test
    public void test(){
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        stringCharsetSortedMap.entrySet().stream().forEach(e->{
            System.out.println(e.getKey()+"="+e.getValue());
        });
    }

    @Test
    public void test1() throws CharacterCodingException {
        Charset gbk = Charset.forName("GBK");
        CharsetDecoder charsetDecoder = gbk.newDecoder();
        CharsetEncoder charsetEncoder = gbk.newEncoder();
        CharBuffer charBuffer = CharBuffer.allocate(120);
        charBuffer.put("你是谁");
        charBuffer.flip();
        //编码，这个过程会最终flip()。
        ByteBuffer encode = charsetEncoder.encode(charBuffer);
        //解码，这个过程会最终flip()
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println(decode.toString());
    }
}
