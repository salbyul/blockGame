package com.game.view;

import java.io.*;

/**
 * 출력 클래스
 */
public class GameWriter implements Closeable {

    private static final String NEXT_LINE = "\n";

    private final BufferedWriter bw;

    public GameWriter(final OutputStream outputStream) {
        this.bw = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    /**
     * System.out.println() 메서드와 동일
     *
     * @param value 출력할 값
     * @throws IOException 입출력으로 인한 예외
     */
    public void println(final String value) throws IOException {
        bw.write(value);
        bw.write(NEXT_LINE);
        bw.flush();
    }

    @Override
    public void close() throws IOException {
        this.bw.close();
    }
}
