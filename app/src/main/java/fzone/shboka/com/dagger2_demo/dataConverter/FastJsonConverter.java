package fzone.shboka.com.dagger2_demo.dataConverter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Created by 王天明 on 2015/12/16 0016.
 */
public class FastJsonConverter implements Converter {

    private String charSet;

    public FastJsonConverter() {
        this("utf-8");
    }

    public FastJsonConverter(String charSet) {
        this.charSet = charSet;
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        String charset = this.charSet;
        if (body.mimeType() != null) {
            charset = MimeUtil.parseCharset(body.mimeType(), charset);
        }
        InputStreamReader isr = null;
        Writer writer = null;
        Object e = null;
        try {
            isr = new InputStreamReader(body.in(), charset);
            writer = new StringWriter();
            int len = 0;
            char[] buff = new char[1024];
            while ((len = isr.read(buff)) != -1) {
                writer.write(buff,0,len);
            }
            writer.flush();
            e = JSON.parseObject(writer.toString(), type);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException var14) {
                    ;
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    ;
                }
            }
        }
        return e;
    }

    @Override
    public TypedOutput toBody(Object o) {
        try {
            return new FastJsonConverter.JsonTypedOutput(JSON.toJSONString(o).getBytes(this.charSet), this.charSet);
        } catch (UnsupportedEncodingException var3) {
            throw new AssertionError(var3);
        }
    }


    private static class JsonTypedOutput implements TypedOutput {
        private final byte[] jsonBytes;
        private final String mimeType;

        JsonTypedOutput(byte[] jsonBytes, String encode) {
            this.jsonBytes = jsonBytes;
            this.mimeType = "application/json; charset=" + encode;
        }

        public String fileName() {
            return null;
        }

        public String mimeType() {
            return this.mimeType;
        }

        public long length() {
            return (long)this.jsonBytes.length;
        }

        public void writeTo(OutputStream out) throws IOException {
            out.write(this.jsonBytes);
        }
    }
}
