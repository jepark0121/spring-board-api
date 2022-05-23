package com.example.springboardapi.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Log4j2
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            InputStream is = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            if (is != null) {
                StringBuffer sb = new StringBuffer();
                while(true) {
                    int data = reader.read();
                    if (data < 0) break;
                    sb.append((char) data);
                }

                String result = cleanXSS(sb.toString());
                body = result.getBytes(StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {}

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    private String cleanXSS(String value) {
        log.info("XSS Filter before : " + value);
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        // value = value.replaceAll("script", "");
        log.info("XSS Filter after : " + value);
        return value;
    }
}
