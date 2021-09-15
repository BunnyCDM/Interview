package com.example.http.base2;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by 24540 on 2017/4/28.
 */

public class CountingRequestBody extends RequestBody{

    private RequestBody delegate;
    private Listener listener;

    private CountingSink countingSink;

    public CountingRequestBody(RequestBody delegate, Listener listener) {
        this.delegate = delegate;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return delegate.contentType();
    }

    @Override
    public long contentLength()  {
        try {
            return delegate.contentLength();
        } catch (IOException e) {
            return -1;
        }
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        countingSink = new CountingSink(sink);

        BufferedSink bufferedSink = Okio.buffer(countingSink);
        delegate.writeTo(bufferedSink);

        bufferedSink.flush();
    }

    protected  final class CountingSink extends ForwardingSink{

        private long byteWriten;


        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            byteWriten += byteCount;
            listener.onRequestProgress(byteWriten,contentLength());
        }
    }

    public interface Listener {
        /**
         * @param byteWrited:已写入字节数
         * @param contentLength：总字节数
         */
        void onRequestProgress(long byteWrited, long contentLength);
    }
}