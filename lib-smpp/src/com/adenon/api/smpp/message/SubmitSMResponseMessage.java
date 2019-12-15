package com.adenon.api.smpp.message;

import java.nio.ByteBuffer;

import com.adenon.api.smpp.common.CommonUtils;

public class SubmitSMResponseMessage {

    private String messageIdentifier = null;

    public SubmitSMResponseMessage() {
    }

    public void parseMessage(final ByteBuffer byteBuffer) throws Exception {
        if (byteBuffer.hasRemaining()) {
            byte[] temp = new byte[65];
            final int charCount = CommonUtils.getCOctetString(temp, byteBuffer);
            this.setMessageIdentifier(new String(temp, 0, charCount));
            temp = null;
        }
    }

    public void fillBody(final ByteBuffer byteBuffer) throws Exception {
        if (this.getMessageIdentifier() == null) {
            byteBuffer.put((byte) 0);
        } else {
            byteBuffer.put(this.getMessageIdentifier().getBytes("iso8859-1"));
            byteBuffer.put((byte) 0);
        }
    }

    public String getMessageIdentifier() {
        return this.messageIdentifier;
    }

    public void setMessageIdentifier(final String messageIdentifier) {
        this.messageIdentifier = messageIdentifier;
    }
}
